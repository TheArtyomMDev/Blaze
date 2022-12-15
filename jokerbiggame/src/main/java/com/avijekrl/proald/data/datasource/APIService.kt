package com.avijekrl.proald.data.datasource

import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("/ssolxc")
    suspend fun testRedirect(
        @Query("advertising_id") advertisingId: String? = null,
        @Query("appsflyer_id") appsflyerId: String? = null,
        @Query("campaign_id") campaignId: String? = null,
        @Query("campaign_name") campaignName: String? = null,
        @Query("af_channel") afChannel: String? = null
    ): Response<Unit>

}