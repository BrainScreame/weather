package com.osenov.weather.data.repositories.impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.osenov.weather.JSON_CITY_FILE_NAME
import com.osenov.weather.data.api.ApiHelper
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.data.repositories.CityRepository
import com.osenov.weather.data.source.db.CityDao
import com.osenov.weather.data.source.db.entitiesRoom.CityEntity
import com.osenov.weather.data.source.preference.PreferencesHelper
import com.osenov.weather.getJsonDataFromAsset
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class CityRepositoryImpl @Inject constructor(
    private val context: Context,
    private val cityDao: CityDao,
    private val apiHelper: ApiHelper,
    private val gson: Gson,
    private val jsonReader: JsonReader,
    private val preferencesHelper: PreferencesHelper
) : CityRepository {

    override suspend fun getCityListFromJson(): ArrayList<City> {
        return withContext(Dispatchers.IO) {
            val jsonFileString = getJsonDataFromAsset(context, JSON_CITY_FILE_NAME)
            val listPersonType = object : TypeToken<List<City>>() {}.type
            Gson().fromJson(jsonFileString, listPersonType)
        }
    }

    override fun getCityListFromJson(limit: Int, offset: Int): List<City> {
        jsonReader.beginArray()
        val cities = ArrayList<City>()
        var cityCounter = 0
        while (jsonReader.hasNext()) {
            val city: City = gson.fromJson(jsonReader, TypeToken.get(City::class.java).type)
            if (city.country == "RU") {
                if (cityCounter >= offset) {
                    if (cities.size < limit) {
                        cities.add(city)
                        cityCounter++
                    } else {
                        break
                    }
                }
            }
        }

        jsonReader.close()

        return cities
    }


    override suspend fun insertAllCities(listCities: ArrayList<CityEntity>) {
        return cityDao.insertAllCities(listCities)
    }

    override suspend fun getCitiesFromDB(limit: Int, offset: Int): List<City> {
        return cityDao.selectAllCities(limit, offset).map { cityEntity -> cityEntity.toCity() }
    }


    override suspend fun getCountryCitiesFromDB(
        searchCity: String,
        cityName: String,
        limit: Int,
        offset: Int
    ): List<City> {
        return cityDao.selectCountryCities("%$searchCity%", cityName, limit, offset)
            .map { cityEntity -> cityEntity.toCity() }
    }

    override suspend fun insertCity(cityEntity: CityEntity) {
        return cityDao.insertCity(cityEntity)
    }

    override suspend fun updateCity(city: City) {
        cityDao.updateCity(city.toCityEntity())
    }

    override fun getCitiesWorkManagerId(): String {
        return preferencesHelper.getCitiesWorkManagerId() ?: ""
    }

    override fun setCitiesWorkManagerId(citiesWorkManagerId: String) {
        preferencesHelper.setCitiesWorkManagerId(citiesWorkManagerId)
    }

    override suspend fun getFavoriteCities(): List<City> {
        return cityDao.selectFavoriteCities().map { cityEntity -> cityEntity.toCity() }
    }


}