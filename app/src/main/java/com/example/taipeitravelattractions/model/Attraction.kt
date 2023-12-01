package com.example.taipeitravelattractions.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Attraction(
    val id: Int,
    val name: String,
    val introduction: String,
    @SerialName("distric") val district: String,
    val address: String,
    val tel: String,
    val email: String,
    @SerialName("official_site") val website: String,
    val remind: String,
    val url: String,
    @SerialName("category") val categories: List<Category>,
    val images: List<MyImage>
) : Parcelable