package com.osenov.weather.data.entities.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Snow(
    @SerializedName("1h")
    val snow1h: Float
) : Parcelable