package com.example.taipeitravelattractions.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.taipeitravelattractions.model.Attraction
import com.example.taipeitravelattractions.network.TravelTaipeiApiService
import com.example.taipeitravelattractions.pagination.AttractionsPagingSource
import kotlinx.coroutines.flow.Flow

class AttractionRepository(private val apiService: TravelTaipeiApiService) : IAttractionRepository {
    override fun getPagingAttractions(): Flow<PagingData<Attraction>> {
        return Pager(
            config = PagingConfig(
                pageSize = 100, enablePlaceholders = false, initialLoadSize = 100
            ),
            pagingSourceFactory = { AttractionsPagingSource(apiService) }
        ).flow
    }

}