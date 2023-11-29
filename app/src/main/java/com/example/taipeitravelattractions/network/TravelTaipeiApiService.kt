package com.example.taipeitravelattractions.network

import com.example.taipeitravelattractions.model.AttractionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TravelTaipeiApiService {
    @Headers("Accept: application/json")
    @GET("zh-tw/Attractions/All")
    suspend fun getAttractions(@Query("page") page: Int = 1): Response<AttractionsResponse>
}