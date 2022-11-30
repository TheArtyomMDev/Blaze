package com.jerbuchameeris.aviajet.utils

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

    const val APPSFLYER_API_KEY = "PEeWLHc4xUbj8cXbJe939j"
    const val ONESIGNAL_API_KEY = "f8fba181-602e-4f26-8a8b-6420e55d3eff"
    const val KOCHAVA_API_KEY = "kocamper-18-jet-9xj"
}