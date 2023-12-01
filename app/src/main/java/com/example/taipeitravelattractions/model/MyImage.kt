package com.example.taipeitravelattractions.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MyImage(
    val src: String,
    @SerialName("ext") val imageType: String = "none"
) : Parcelable
