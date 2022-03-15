package com.osenov.weather.data.entities.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyFeelsLikeTemperature(
    @SerializedName("morn")
    val morningTemperature: Float,

    @SerializedName("day")
    val dayTemperature: Float,

    @SerializedName("eve")
    val eveningTemperature: Float,

    @SerializedName("night")
    val nightTemperature: Float,
) : Parcelable