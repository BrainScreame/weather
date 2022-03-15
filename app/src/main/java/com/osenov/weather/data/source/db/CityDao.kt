package com.osenov.weather.data.source.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.osenov.weather.data.source.db.entitiesRoom.CityEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCity(cityEntity: CityEntity)

    @Insert
    suspend fun insertAllCities(list: ArrayList<CityEntity>)

    @Query("SELECT * from CityEntity LIMIT :limit OFFSET :offset")
    suspend fun selectAllCities(limit: Int, offset: Int): List<CityEntity>

    @Query("SELECT * from CityEntity Where  cityCountry = :countryName AND cityName LIKE :searchCity ORDER BY cityFavorite DESC, cityName LIMIT :limit OFFSET :offset")
    suspend fun selectCountryCities(
        searchCity: String,
        countryName: String,
        limit: Int,
        offset: Int
    ): List<CityEntity>

    @Query("SELECT * from CityEntity WHERE cityFavorite == 1 ORDER BY cityName")
    suspend fun selectFavoriteCities(): List<CityEntity>

    @Update
    suspend fun updateCity(cityEntity: CityEntity)
}