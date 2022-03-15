package com.osenov.weather.data.entities.weather

import com.google.gson.annotations.SerializedName
import com.osenov.weather.data.entities.weather.*

data class WeatherApi(
    @SerializedName("lon")
    val longitude: Float,

    @SerializedName("lat")
    val latitude: Float,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("timezone_offset")
    val timezoneOffset: Int,

    @SerializedName("current")
    val currentWeather: CurrentWeather,

    @SerializedName("minutely")
    val minutelyWeather: ArrayList<MinutelyWeather>,

    @SerializedName("hourly")
    var hourlyWeather: ArrayList<HourlyWeather>,

    @SerializedName("daily")
    val dailyWeather: ArrayList<DailyWeather>,

    @SerializedName("alerts")
    val alerts : ArrayList<AlertsWeather>,
)