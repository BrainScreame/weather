package com.osenov.weather.data.repositories

import com.osenov.weather.data.entities.weather.WeatherApi
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherApi(latitude: Float, longitude: Float) : Response<WeatherApi>
}