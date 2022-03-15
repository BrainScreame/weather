package com.osenov.weather.data.entities.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourlyWeather(
    @SerializedName("dt")
    var time: Long,

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

    @SerializedName("pop")
    val probabilityPrecipitation: Float,

    @SerializedName("rain")
    val rain: Rain,

    @SerializedName("snow")
    val snow: Snow,

    @SerializedName("weather")
    val weather: ArrayList<Weather>

) : Parcelable