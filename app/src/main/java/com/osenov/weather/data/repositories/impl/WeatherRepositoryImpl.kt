package com.osenov.weather.data.repositories.impl

import com.osenov.weather.data.api.ApiHelper
import com.osenov.weather.data.entities.weather.WeatherApi
import com.osenov.weather.data.repositories.WeatherRepository
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : WeatherRepository {

    override suspend fun getWeatherApi(latitude: Float, longitude: Float): Response<WeatherApi> {
        return apiHelper.getWeather(latitude, longitude)
    }
}