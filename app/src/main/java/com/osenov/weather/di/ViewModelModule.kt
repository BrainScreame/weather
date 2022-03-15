package com.osenov.weather.di

import androidx.lifecycle.ViewModel
import com.osenov.weather.WeatherApp
import com.osenov.weather.data.repositories.CityRepository
import com.osenov.weather.data.repositories.LocationRepository
import com.osenov.weather.data.repositories.WeatherRepository
import com.osenov.weather.ui.main.city_list.CityListViewModel
import com.osenov.weather.ui.main.main.MainViewModel
import com.osenov.weather.ui.main.weather_city.WeatherCityViewModel
import com.osenov.weather.ui.main.weather_city.weather_days.WeatherDayViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {
    @IntoMap
    @ViewModelKey(CityListViewModel::class)
    @Provides
    fun provideCityListViewModel(
        application: WeatherApp,
        cityRepository: CityRepository
    ): ViewModel {
        return CityListViewModel(application, cityRepository)
    }

    @IntoMap
    @ViewModelKey(WeatherCityViewModel::class)
    @Provides
    fun provideWeatherCityViewModel(
        weatherRepository: WeatherRepository,
        cityRepository: CityRepository
    ): ViewModel {
        return WeatherCityViewModel(weatherRepository, cityRepository)
    }

    @IntoMap
    @ViewModelKey(WeatherDayViewModel::class)
    @Provides
    fun provideWeatherDayViewModel(weatherRepository: WeatherRepository): ViewModel {
        return WeatherDayViewModel(weatherRepository)
    }

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Provides
    fun provideLocationViewModel(locationRepository: LocationRepository): ViewModel {
        return MainViewModel(locationRepository)
    }
}