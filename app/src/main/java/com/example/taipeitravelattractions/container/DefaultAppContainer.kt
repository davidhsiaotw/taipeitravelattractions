package com.example.taipeitravelattractions.container

import com.example.taipeitravelattractions.network.TravelTaipeiApiService
import com.example.taipeitravelattractions.repository.AttractionRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * implementation for the DI container at the application level.
 *
 * variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer() : AppContainer {
    private val baseUrl = "https://www.travel.taipei/open-api/"
    private val json: Json =
        Json { ignoreUnknownKeys = true }  // https://stackoverflow.com/a/58355285
    private val retrofit = Retrofit.Builder().addConverterFactory(
        json.asConverterFactory("application/json".toMediaType())
    ).baseUrl(baseUrl).build()

    /**
     * Retrofit service object for creating api calls
     */
    private val travelTaipeiApiService: TravelTaipeiApiService by lazy {
        retrofit.create(TravelTaipeiApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val attractionRepository: AttractionRepository by lazy {
        AttractionRepository(travelTaipeiApiService)
    }
}