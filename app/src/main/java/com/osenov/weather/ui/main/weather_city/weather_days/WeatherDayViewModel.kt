package com.osenov.weather.ui.main.weather_city.weather_days

import androidx.lifecycle.ViewModel
import com.osenov.weather.data.repositories.WeatherRepository
import javax.inject.Inject

class WeatherDayViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

}