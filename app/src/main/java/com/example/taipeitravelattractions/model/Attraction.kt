package com.example.taipeitravelattractions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attraction(
    val id: Int,
    val name: String,
    val introduction: String,
    @SerialName("distric") val district: String,
    val address: String,
    val tel: String,
    val email: String,
    val nlat: Double,
    val elong: Double,
    @SerialName("official_site") val website: String,
    val remind: String,
    val url: String,
    @SerialName("category") val categories: List<Category>,
    val images: List<MyImage>
)