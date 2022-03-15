package com.osenov.weather.data.entities.weather

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("dt")
    val time: Long,

    @SerializedName("sunrise")
    val sunrise: Long,

    @SerializedName("sunset")
    val sunset: Long,

    @SerializedName("temp")
    val temperature: Float,

    @SerializedName("feels_like")
    val temperatureFeelsLike: Float,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("dew_point")
    val atmosphericTemperature: Float,

    @SerializedName("uvi")
    val UVIndex: Float,

    @SerializedName("clouds")
    val clouds: Int,

    @SerializedName("visibility")
    val visibility: Float,

    @SerializedName("wind_speed")
    val windSpeed: Float,

    @SerializedName("wind_gust")
    val windGust: Float,

    @SerializedName("wind_deg")
    val windDeg: Float,

    @SerializedName("rain")
    val rain: Rain,

    @SerializedName("snow")
    val snow: Snow,

    @SerializedName("weather")
    val weather: ArrayList<Weather>,
)