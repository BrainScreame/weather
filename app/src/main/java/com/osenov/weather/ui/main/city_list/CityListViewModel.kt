package com.osenov.weather.ui.main.city_list

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.osenov.weather.CitiesUploadWorker
import com.osenov.weather.WeatherApp
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.data.repositories.CityRepository
import kotlinx.coroutines.launch
import retrofit2.http.Query
import java.util.*
import javax.inject.Inject

@SuppressLint("RestrictedApi")
class CityListViewModel @Inject constructor(
    application: WeatherApp,
    private val cityRepository: CityRepository
) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)
    var citiesWorkManager: LiveData<WorkInfo>? = null

    // так можно?
    init {
        val idWorkManager = cityRepository.getCitiesWorkManagerId()
        citiesWorkManager = if (idWorkManager == "") {
            val workRequest = OneTimeWorkRequestBuilder<CitiesUploadWorker>().build()
            workManager.enqueue(workRequest)
            cityRepository.setCitiesWorkManagerId(workRequest.stringId)
            workManager.getWorkInfoByIdLiveData(workRequest.id)
        } else {
            workManager.getWorkInfoByIdLiveData(UUID.fromString(idWorkManager))
        }

    }

    private val query = MutableLiveData("")

    fun searchCities(query: String) {
        this.query.value = query.replace("\\s+".toRegex(), "")
    }

    fun updateCity(city: City) {
        viewModelScope.launch {
            cityRepository.updateCity(city)
        }
    }

    val flow = Transformations.switchMap(query) { queryString ->
        Pager(PagingConfig(pageSize = 100, enablePlaceholders = true)) {
            CitiesPagingSource(cityRepository, queryString)
        }.liveData
            .cachedIn(viewModelScope)
    }


}