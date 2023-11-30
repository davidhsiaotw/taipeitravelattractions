package com.example.taipeitravelattractions.container

import com.example.taipeitravelattractions.repository.AttractionRepository

/**
 * dependency injection container at the application level.
 */
interface AppContainer {
    val attractionRepository: AttractionRepository
}