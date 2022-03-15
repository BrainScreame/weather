package com.osenov.weather.data.source.db.entitiesRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class CoordinateEntity(
    @ColumnInfo(name = "cityLongitude")
    val longitude: Float,

    @ColumnInfo(name = "cityLatitude")
    val latitude: Float
)