package com.osenov.weather.data.entities.location

import com.google.gson.annotations.SerializedName

data class CellLocation(
    val status: String,
    val message: String?,
    val accuracy: Int? = null,
    val address: String? = null,

    @SerializedName("lat")
    val latitude: Double? = null,

    @SerializedName("lon")
    val longitude: Double? = null
) {
    fun isSuccess() = status == "ok"
}