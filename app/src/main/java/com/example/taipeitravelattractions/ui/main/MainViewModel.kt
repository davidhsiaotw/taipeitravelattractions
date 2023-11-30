package com.example.taipeitravelattractions.ui.main

import com.example.taipeitravelattractions.TaipeiTravelAttractionsApplication
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.taipeitravelattractions.model.Attraction
import com.example.taipeitravelattractions.repository.AttractionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

enum class AttractionUiState {
    LOADING, SUCCESS, FAIL
}

class MainViewModel(private val attractionRepository: AttractionRepository) : ViewModel() {
    var attractionUiState: AttractionUiState = AttractionUiState.LOADING
    var attractions: Flow<PagingData<UiModel>> = emptyFlow()
        private set

    init {
        getAttractions()
    }

    fun getAttractions() {
        try {
            attractions = attractionRepository.getPagingAttractions().map { pagingData ->
                pagingData.map { UiModel.AttractionItem(it) as UiModel }
            }.cachedIn(viewModelScope)
            attractionUiState = AttractionUiState.SUCCESS
        } catch (e: Exception) {
            attractionUiState = AttractionUiState.FAIL
        }
    }

    /**
     * Factory for [MainViewModel] that takes [AttractionRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TaipeiTravelAttractionsApplication)
                val attractionRepository = application.container.attractionRepository
                MainViewModel(attractionRepository) // inject dependency
            }
        }
    }

}

/**
 * [reference](https://developer.android.com/topic/libraries/architecture/paging/v3-transform#convert-ui-model)
 */
sealed class UiModel {
    data class AttractionItem(val attraction: Attraction) : UiModel()
}