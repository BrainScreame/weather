package com.osenov.weather.data.repositories

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.data.source.db.entitiesRoom.CityEntity
import retrofit2.Response


interface CityRepository {
    suspend fun getCitiesFromDB(limit : Int, offset : Int): List<City>
    suspend fun getCountryCitiesFromDB(searchCity: String, cityName : String, limit : Int, offset : Int): List<City>
    fun getCityListFromJson(limit : Int, offset : Int): List<City>

    suspend fun insertAllCities(listCities : ArrayList<CityEntity>)
    suspend fun insertCity(cityEntity: CityEntity)
    suspend fun getCityListFromJson(): ArrayList<City>

    suspend fun updateCity(city : City)

    fun getCitiesWorkManagerId() : String
    fun setCitiesWorkManagerId(citiesWorkManagerId: String)

    suspend fun getFavoriteCities() : List<City>


}