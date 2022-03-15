package com.osenov.weather.ui.main.city_list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.osenov.weather.CITIES_PAGE_SIZE
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.data.repositories.CityRepository
import kotlinx.coroutines.delay
import retrofit2.http.Query


class CitiesPagingSource(
    private val repository: CityRepository,
    private val query: String?
) :
    PagingSource<Int, City>() {

    override fun getRefreshKey(state: PagingState<Int, City>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, City> {
        try {
            val page = params.key ?: 0
            val offset = if (page == 0) 0 else (page + 2) * CITIES_PAGE_SIZE

            var cities: List<City>

            repeat(6){
                cities = repository.getCountryCitiesFromDB(
                    query ?: "",
                    cityName = "RU",
                    offset = offset,
                    limit = params.loadSize
                )
                if(cities.isNotEmpty()){
                    return@repeat
                }
                delay(1500)
            }

            cities = repository.getCountryCitiesFromDB(
                query ?: "",
                cityName = "RU",
                offset = offset,
                limit = params.loadSize
            )

            return LoadResult.Page(
                data = cities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (cities.size < params.loadSize) null else page + 1
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }


}
