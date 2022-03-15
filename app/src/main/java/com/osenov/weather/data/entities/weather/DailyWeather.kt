package com.osenov.weather.data.entities.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyWeather(
    @SerializedName("dt")
    val time: Long,

    @SerializedName("sunrise")
    val sunrise: Long,

    @SerializedName("sunset")
    val sunset: Long,

    @SerializedName("moonrise")
    val moonrise: Long,

    @SerializedName("moonset")
    val moonset: Long,

    @SerializedName("moon_phase")
    val moonPhase: Float,

    @SerializedName("temp")
    val dailyTemperature: DailyTemperature,

    @SerializedName("feels_like")
    val dailyFeelsLikeTemperature: DailyFeelsLikeTemperature,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("dew_point")
    val atmosphericTemperature: Float,

    @SerializedName("uvi")
    val UVIndex: Float,

    @SerializedName("pop")
    val probabilityPrecipitation: Float,

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
    val rain: Float,

    @SerializedName("snow")
    val snow: Float,

    @SerializedName("weather")
    val weather: ArrayList<Weather>
) : Parcelable