package com.slblzoctoplaymgte.opeir.ui.main

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.slblzoctoplaymgte.opeir.ui.components.MainMenuView
import com.slblzoctoplaymgte.opeir.ui.components.LoadingView
import com.slblzoctoplaymgte.opeir.ui.components.SpinView
import com.slblzoctoplaymgte.opeir.ui.components.WebView
import com.slblzoctoplaymgte.opeir.ui.theme.UniQuizTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UniQuizTheme {
                val screenState by vm.screenStateFlow.collectAsState()
                val points by vm.pointsFlow.collectAsState()
                val requestState by vm.requestStateFlow.collectAsState()

                vm.writePoints(points)



                val activity = LocalContext.current as Activity
                when(requestState) {
                    is RequestState.Success -> {
                        with(rememberSystemUiController()) {
                            isSystemBarsVisible = true
                        }
                        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        WebView(url = (requestState as RequestState.Success).url)
                    }
                    else -> {
                        with(rememberSystemUiController()) {
                            isSystemBarsVisible = false
                        }
                        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        when(screenState) {
                            is ScreenState.Playing -> {
                                SpinView(points, onHomeClick = {vm.onHome()}) {}
                            }
                            is ScreenState.Choosing -> {
                                MainMenuView(points) {
                                    vm.onNewGame()
                                }
                            }
                            is ScreenState.Loading -> {
                                LoadingView((screenState as ScreenState.Loading).progress)
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true, widthDp = 2340, heightDp = 1080)
    @Composable
    fun DefaultPreview() {
        UniQuizTheme {
            //ChoosingView()
            LoadingView(progress = 0.7F)
            //QuestionView(Question("Default question"), QuizTopic.Players) {}
        }
    }
}