package com.example.taipeitravelattractions.ui

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

object AnimationCreator {

    fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}