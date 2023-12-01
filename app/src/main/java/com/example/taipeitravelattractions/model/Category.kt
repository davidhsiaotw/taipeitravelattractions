package com.example.taipeitravelattractions.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Category(val id: Int, val name: String) : Parcelable