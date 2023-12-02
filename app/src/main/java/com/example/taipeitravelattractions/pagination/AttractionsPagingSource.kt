package com.example.taipeitravelattractions.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.taipeitravelattractions.model.Attraction
import com.example.taipeitravelattractions.network.TravelTaipeiApiService

class AttractionsPagingSource(
    private val apiService: TravelTaipeiApiService
) : PagingSource<Int, Attraction>() {
    override fun getRefreshKey(state: PagingState<Int, Attraction>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Attraction> {
        val startKey = params.key ?: 1
        val pageSize = params.loadSize
        val page = startKey / params.loadSize + 1

        try {
            val response = apiService.getAttractions(page)

            return if (response.isSuccessful) {
                val attractions = response.body()?.data ?: listOf()

                LoadResult.Page(
                    data = attractions,
                    prevKey = if (page == 1) null else startKey - 1,
                    nextKey = startKey + pageSize
                )
            } else {
                Log.e("AttractionsPagingSource.load", "api response: ${response.code()}")
                LoadResult.Error(Exception("Network request failed with code: ${response.code()}"))
            }

        } catch (e: Exception) {
            Log.e("AttractionsPagingSource.load", e.message ?: "paging load error")
            return LoadResult.Error(e)
        }
    }
}