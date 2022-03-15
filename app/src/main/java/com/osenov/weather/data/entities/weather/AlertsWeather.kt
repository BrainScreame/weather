package com.osenov.weather.data.entities.weather

import com.google.gson.annotations.SerializedName

data class AlertsWeather(
    @SerializedName("sender_name")
    val senderName: String,

    @SerializedName("event")
    val eventName: String,

    @SerializedName("start")
    val startTime: Long,

    @SerializedName("end")
    val endTime: Long,

    @SerializedName("description")
    val description: String,

    @SerializedName("tags")
    val tags: ArrayList<String>,


    )
