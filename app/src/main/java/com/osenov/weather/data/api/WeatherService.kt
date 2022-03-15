package com.osenov.weather.data.api

import com.osenov.weather.APPLICATION_ID
import com.osenov.weather.UNIT_DEFAULT
import com.osenov.weather.data.entities.weather.WeatherApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") appId : String = APPLICATION_ID,
        @Query("units") units : String = UNIT_DEFAULT
    ): Response<WeatherApi>
}