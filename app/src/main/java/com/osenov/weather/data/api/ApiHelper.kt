package com.osenov.weather.data.api

import com.osenov.weather.data.entities.location.CellInfo
import com.osenov.weather.data.entities.location.CellLocation
import com.osenov.weather.data.entities.weather.WeatherApi
import retrofit2.Response

interface ApiHelper {

    suspend fun getWeather(latitude: Float, longitude: Float): Response<WeatherApi>

    suspend fun getLocation(cellInfo: CellInfo): Response<CellLocation>
}