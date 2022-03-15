package com.osenov.weather.data.entities.city

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.osenov.weather.data.source.db.entitiesRoom.CityEntity
import com.osenov.weather.data.source.db.entitiesRoom.CoordinateEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinate(
    @SerializedName("lon")
    val longitude: Float,

    @SerializedName("lat")
    val latitude: Float
) : Parcelable {
    fun toCoordinateEntity() = CoordinateEntity(longitude, latitude)
}