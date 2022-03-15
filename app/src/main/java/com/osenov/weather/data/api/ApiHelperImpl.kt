package com.osenov.weather.data.api

import com.osenov.weather.data.entities.location.CellInfo
import com.osenov.weather.data.entities.location.CellLocation
import com.osenov.weather.data.entities.weather.WeatherApi
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val locationService: LocationService
) : ApiHelper {
    override suspend fun getWeather(
        latitude: Float,
        longitude: Float
    ): Response<WeatherApi> = weatherService.getWeather(latitude, longitude)

    override suspend fun getLocation(cellInfo: CellInfo): Response<CellLocation> =
        locationService.getLocationByCellInfo(cellInfo)
}