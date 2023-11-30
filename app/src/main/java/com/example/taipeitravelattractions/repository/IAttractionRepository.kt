package com.example.taipeitravelattractions.repository

import androidx.paging.PagingData
import com.example.taipeitravelattractions.model.Attraction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface IAttractionRepository {
    fun getPagingAttractions(): Flow<PagingData<Attraction>>

    /**
     * this object is for learning purpose
     *
     * create a default implementation of [IAttractionRepository]
     *
     */
    companion object : IAttractionRepository {
        override fun getPagingAttractions(): Flow<PagingData<Attraction>> {
            return emptyFlow()
        }
    }
}