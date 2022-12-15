package com.avijekrl.proald.utils

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val TAG = "BlazeWheel"

    val POINTS = intPreferencesKey("points")
    val ADVERTISING_ID = stringPreferencesKey("advertising_id")
    val APPSFLYER_ID = stringPreferencesKey("appsflyer_id")
    val CAMPAIGN_ID = stringPreferencesKey("campaign_id")
    val CAMPAIGN_NAME = stringPreferencesKey("campaign")
    val AF_CHANNEL = stringPreferencesKey("af_channel")

    const val API_URL = "https://capspotz.com"
    const val MAIN_URL = "https://capspotz.com/ssolxc"

    const val APPSFLYER_API_KEY = "7RrB8MAJVYsXNjVLsQtHLQ"
    const val ONESIGNAL_API_KEY = "f3f60ba2-0cd8-4ef4-9a43-5cd9a9ca1016"
    const val KOCHAVA_API_KEY = "kocamper-21-avia-qtz5kv"
}