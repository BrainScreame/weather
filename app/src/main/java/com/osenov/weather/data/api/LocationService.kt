package com.osenov.weather.data.api

import com.osenov.weather.APPLICATION_ID
import com.osenov.weather.UNIT_DEFAULT
import com.osenov.weather.data.entities.location.CellInfo
import com.osenov.weather.data.entities.location.CellLocation
import com.osenov.weather.data.entities.weather.WeatherApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LocationService {

    @POST("v2/process.php")
    suspend fun getLocationByCellInfo(@Body cellInfo: CellInfo): Response<CellLocation>

}