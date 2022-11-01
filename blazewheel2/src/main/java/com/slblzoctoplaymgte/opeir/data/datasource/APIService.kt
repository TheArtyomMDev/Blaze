package com.slblzoctoplaymgte.opeir.data.datasource

import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("/aaadfgo")
    suspend fun testConnection(
        @Query("advertising_id") advertisingId: String? = null,
        @Query("appsflyer_id") appsflyerId: String? = null,
        @Query("campaign_id") campaignId: String? = null,
        @Query("campaign_name") campaignName: String? = null,
        @Query("af_channel") afChannel: String? = null
    ): Response<Unit>

}