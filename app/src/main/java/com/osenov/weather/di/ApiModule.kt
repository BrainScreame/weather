package com.osenov.weather.di

import com.google.gson.Gson
import com.osenov.weather.LOCATION_API_URL
import com.osenov.weather.WEATHER_API_URL
import com.osenov.weather.data.api.ApiHelper
import com.osenov.weather.data.api.ApiHelperImpl
import com.osenov.weather.data.api.LocationService
import com.osenov.weather.data.api.WeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson) : GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


    @Provides
    @Singleton
    fun provideWeatherService(gsonConverterFactory: GsonConverterFactory): WeatherService {
        return Retrofit.Builder()
            .baseUrl(WEATHER_API_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationService(gsonConverterFactory: GsonConverterFactory): LocationService {
        return Retrofit.Builder()
            .baseUrl(LOCATION_API_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(LocationService::class.java)
    }


}