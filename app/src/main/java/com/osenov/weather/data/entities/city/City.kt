package com.osenov.weather.data.entities.city

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.osenov.weather.data.source.db.entitiesRoom.CityEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val id: Long,
    val name: String,
    val state: String,
    val country: String,

    @SerializedName("coord")
    val coordinate: Coordinate,

    var favorite : Boolean,
) : Parcelable {
    fun toCityEntity() = CityEntity(id, name, state, country, coordinate.toCoordinateEntity(), favorite)
}
