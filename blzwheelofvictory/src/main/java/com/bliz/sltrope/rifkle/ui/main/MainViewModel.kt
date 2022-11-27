package com.bliz.sltrope.rifkle.ui.main

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.bliz.sltrope.rifkle.utils.Constants
import com.kochava.tracker.events.Event
import com.kochava.tracker.events.EventType
import io.branch.referral.util.BRANCH_STANDARD_EVENT
import io.branch.referral.util.BranchEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.tasks.await

class MainViewModel(
    private val dataStore: DataStore<Preferences>,
    private val context: Context
): ViewModel() {
    private val _screenStateFlow: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Choosing)
    private val _pointsFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _requestStateFlow: MutableStateFlow<RequestState> = MutableStateFlow(RequestState.Loading)

    val screenStateFlow: StateFlow<ScreenState> = _screenStateFlow
    val pointsFlow: StateFlow<Int> = _pointsFlow
    val requestStateFlow: StateFlow<RequestState> = _requestStateFlow

    init {
        updateConnectionStatus()
        viewModelScope.launch {
            dataStore.data.collectLatest {
                val points = it[Constants.POINTS]
                println("GETTING POINTS $points")
                if (points != null) {
                    _pointsFlow.emit(points)
                }
            }
        }
        viewModelScope.launch {
            _screenStateFlow.emit(ScreenState.Loading(0.1F))
            while(requestStateFlow.value == RequestState.Loading)
                for(progress in 1..10) {
                    _screenStateFlow.emit(ScreenState.Loading(progress/10F))
                    delay(100)
                }

            if(requestStateFlow.value !is RequestState.Success)
                _screenStateFlow.emit(ScreenState.Choosing)
        }
        sendEvents()
    }

    private fun updateConnectionStatus() {
        CoroutineScope(Dispatchers.IO).launch {
            val urlFromDatabase = getUrlFromDatabase()
            if (urlFromDatabase.isBlank()) {
                _requestStateFlow.emit(RequestState.Failed)
            } else {
                dataStore.data.collectLatest { prefs ->
                    if(prefs[Constants.CAMPAIGN_NAME].isNullOrBlank())
                        delay(3000)

                    var urlWithParams = "$urlFromDatabase?"
                    urlWithParams += "advertising_id=" + prefs[Constants.ADVERTISING_ID] + "&"
                    urlWithParams += "appsflyer_id=" + prefs[Constants.APPSFLYER_ID] + "&"
                    urlWithParams += "campaign_id=" + ((prefs[Constants.CAMPAIGN_ID]) ?: "") + "&"
                    urlWithParams += "campaign_name=" + ((prefs[Constants.CAMPAIGN_NAME])
                        ?: "") + "&"
                    urlWithParams += "af_channel=" + ((prefs[Constants.AF_CHANNEL]) ?: "")

                    println("urlWithParams : $urlWithParams")
                    _requestStateFlow.emit(RequestState.Success(urlWithParams))
                }
            }
        }
    }

    fun writePoints(points: Int) {
        viewModelScope.launch {
            dataStore.edit { settings ->
                if (points == 0) {
                    val pointsDataStore = settings[Constants.POINTS]
                    if(pointsDataStore == null)
                        settings[Constants.POINTS] = 0
                } else {
                    println("Writing points $points")
                    settings[Constants.POINTS] = points
                }

            }
        }
    }

    fun onHome() {
        viewModelScope.launch {
            _screenStateFlow.emit(ScreenState.Choosing)
        }
    }

    fun onNewGame() {
        viewModelScope.launch {
            _screenStateFlow.emit(ScreenState.Playing)
        }
    }

    private suspend fun getUrlFromDatabase(): String {
        return try {
            withTimeout(10 * 1000L) {
                println("Started fetching")
                val database = Firebase.database
                val myRef = database.getReference("url")

                val fetchedUrl = myRef.get().await().value.toString()
                println("fetchedUrl : $fetchedUrl")
                fetchedUrl
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    private fun sendEvents() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.data.collectLatest { prefs ->
                println("AF CHANNEL is ${prefs[Constants.AF_CHANNEL]}")
                when(prefs[Constants.AF_CHANNEL]) {
                    "ACI_Search" -> {
                        BranchEvent(BRANCH_STANDARD_EVENT.ACHIEVE_LEVEL)
                            .setDescription("ACI_Search")
                            .logEvent(context)

                        Event
                            .buildWithEventType(EventType.ACHIEVEMENT)
                            .setName("ACI_Search")
                            .send()
                    }
                    "ACI_Youtube" -> {
                        BranchEvent(BRANCH_STANDARD_EVENT.SHARE)
                            .setDescription("ACI_Youtube")
                            .logEvent(context)
                        Event
                            .buildWithEventType(EventType.DEEPLINK)
                            .setName("ACI_Youtube")
                            .send()
                    }
                    "ACI_Display" -> {
                        BranchEvent(BRANCH_STANDARD_EVENT.RATE)
                            .setDescription("ACI_Search")
                            .logEvent(context)
                        Event
                            .buildWithEventType(EventType.RATING)
                            .setName("ACI_Search")
                            .send()
                    }
                    else -> {
                        BranchEvent(BRANCH_STANDARD_EVENT.VIEW_AD)
                            .setDescription("NoChannel")
                            .logEvent(context)
                        Event
                            .buildWithEventType(EventType.VIEW)
                            .setName("NoChannel")
                            .send()
                        println("Event sent")
                    }
                }
            }
        }
    }
}