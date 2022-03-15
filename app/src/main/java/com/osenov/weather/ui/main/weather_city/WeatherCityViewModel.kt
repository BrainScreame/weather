package com.osenov.weather.ui.main.weather_city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.data.entities.weather.WeatherApi
import com.osenov.weather.data.repositories.CityRepository
import com.osenov.weather.data.repositories.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherCityViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val citiesRepository: CityRepository
) : ViewModel() {

    private var weather = MutableLiveData<WeatherApi>()
    val weatherLiveData: LiveData<WeatherApi> = weather

    private var favoriteCities = MutableLiveData<List<City>>()
    val favoriteCitiesLiveData: LiveData<List<City>> = favoriteCities

    fun getFavoriteCities() {
        viewModelScope.launch {
            favoriteCities.value = citiesRepository.getFavoriteCities()
        }
    }


    fun getWeather(city: City) {
        viewModelScope.launch {
            val response =
                weatherRepository.getWeatherApi(city.coordinate.latitude, city.coordinate.longitude)
            if (response.isSuccessful) {
                weather.value = response.body()
            }
        }
    }
}