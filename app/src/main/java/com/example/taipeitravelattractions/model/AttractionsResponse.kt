package com.example.taipeitravelattractions.model

import kotlinx.serialization.Serializable

@Serializable
data class AttractionsResponse(val total: Int, val data: List<Attraction>)