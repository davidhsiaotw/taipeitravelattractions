package com.example.taipeitravelattractions

import android.app.Application
import com.example.taipeitravelattractions.container.AppContainer
import com.example.taipeitravelattractions.container.DefaultAppContainer

class TaipeiTravelAttractionsApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}