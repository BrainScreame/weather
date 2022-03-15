package com.osenov.weather.data.source.db.entitiesRoom

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.data.entities.city.Coordinate

@Entity
data class CityEntity(
    @PrimaryKey @ColumnInfo(name = "cityId")
    val id: Long,

    @ColumnInfo(name = "cityName")
    val name: String,

    @ColumnInfo(name = "cityState")
    val state: String,

    @ColumnInfo(name = "cityCountry")
    val country: String,

    @Embedded
    val coordinate: CoordinateEntity,

    @ColumnInfo(name = "cityFavorite")
    var favorite: Boolean,

) {
    fun toCity() = City(id, name, state, country, Coordinate(coordinate.longitude, coordinate.latitude), favorite)
}