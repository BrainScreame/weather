package com.osenov.weather.di

import com.osenov.weather.data.api.ApiHelper
import com.osenov.weather.data.api.ApiHelperImpl
import com.osenov.weather.data.repositories.CityRepository
import com.osenov.weather.data.repositories.LocationRepository
import com.osenov.weather.data.repositories.impl.CityRepositoryImpl
import com.osenov.weather.data.repositories.WeatherRepository
import com.osenov.weather.data.repositories.impl.LocationRepositoryImpl
import com.osenov.weather.data.repositories.impl.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Suppress("FunctionName")
    @Binds
    fun bindCityRepositoryImpl_to_CityRepository(
        cityRepositoryImpl: CityRepositoryImpl
    ): CityRepository

    @Suppress("FunctionName")
    @Binds
    fun bindWeatherRepositoryImpl_to_WeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Suppress("FunctionName")
    @Binds
    fun bindLocationRepositoryImpl_to_LocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository

    @Suppress("FunctionName")
    @Binds
    fun bindApiHelperImpl_to_ApiHelper(apiHelper: ApiHelperImpl): ApiHelper

}