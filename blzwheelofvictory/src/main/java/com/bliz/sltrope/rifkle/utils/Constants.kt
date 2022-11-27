package com.bliz.sltrope.rifkle.utils

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

    const val APPSFLYER_API_KEY = "dMiVoRRtuHnNogG3F64RhC"
    const val ONESIGNAL_API_KEY = "dfb71a96-3424-4d03-8eda-e751d46af772"
    const val KOCHAVA_API_KEY = "kocamper-16-ukcr5ex2l"
}