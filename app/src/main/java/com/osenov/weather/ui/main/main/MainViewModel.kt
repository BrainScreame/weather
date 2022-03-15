package com.osenov.weather.ui.main.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.data.entities.location.CellLocation
import com.osenov.weather.data.repositories.LocationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val locationRepository: LocationRepository) : ViewModel() {

    private var location = MutableLiveData<CellLocation>()
    val locationLiveData : LiveData<CellLocation> = location

    fun getLocation() {
        viewModelScope.launch {
            location.value = locationRepository.getLocationApi().body()
        }
    }

}