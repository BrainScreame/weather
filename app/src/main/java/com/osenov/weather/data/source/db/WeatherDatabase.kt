package com.osenov.weather.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.osenov.weather.data.source.db.WeatherDatabase.Companion.VERSION
import com.osenov.weather.data.source.db.entitiesRoom.CityEntity

@Database(entities = [CityEntity::class], version = VERSION)
abstract class WeatherDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "weather.db"
        const val VERSION = 1
    }

    abstract fun cityDao(): CityDao

}