package com.example.taipeitravelattractions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyImage(
    val src: String,
    @SerialName("ext") val imageType: String
)
