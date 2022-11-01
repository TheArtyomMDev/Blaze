package com.slblzoctoplaymgte.opeir.utils

import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.slblzoctoplaymgte.opeir.R

object Constants {
    const val TAG = "BlazeWheel"

    val POINTS = intPreferencesKey("points")
    val ADVERTISING_ID = stringPreferencesKey("advertising_id")
    val APPSFLYER_ID = stringPreferencesKey("appsflyer_id")
    val CAMPAIGN_ID = stringPreferencesKey("campaign_id")
    val CAMPAIGN_NAME = stringPreferencesKey("campaign_name")
    val AF_CHANNEL = stringPreferencesKey("af_channel")

    const val API_URL = "https://capspotz.com"

    const val APPSFLYER_API_KEY = "ZybkjxZc5UsXm4uUaxDFHG"
    const val ONESIGNAL_API_KEY = "b978e763-a4a4-4e04-9731-026a452231c8"
    // const val KOCHAVA_API_KEY = "kocom-appcano-ziomn-u0ctevjn3"
    const val MYTRACKER_API_KEY = "67467068137378013631"
}