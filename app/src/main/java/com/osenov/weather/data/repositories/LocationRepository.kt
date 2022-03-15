package com.osenov.weather.data.repositories

import com.osenov.weather.data.entities.location.CellInfo
import com.osenov.weather.data.entities.location.CellLocation
import com.osenov.weather.data.entities.weather.WeatherApi
import retrofit2.Response

interface LocationRepository {
    suspend fun getLocationApi(): Response<CellLocation>
}