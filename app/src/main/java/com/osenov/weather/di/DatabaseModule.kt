package com.osenov.weather.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.osenov.weather.data.source.db.CityDao
import com.osenov.weather.data.source.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, WeatherDatabase.DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCityDao(weatherDatabase: WeatherDatabase): CityDao {
        return weatherDatabase.cityDao()
    }


}