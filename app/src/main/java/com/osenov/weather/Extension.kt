package com.osenov.weather

import android.content.Context
import androidx.fragment.app.Fragment
import com.osenov.weather.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is WeatherApp -> appComponent
        else -> this.applicationContext.appComponent
    }