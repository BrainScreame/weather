package com.osenov.weather.data.entities.weather

import com.google.gson.annotations.SerializedName

data class MinutelyWeather (
    @SerializedName("dt")
    val time: Long,

    @SerializedName("precipitation")
    val precipitation: Float,
)