package com.osenov.weather

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.data.repositories.CityRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CitiesUploadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val cityRepository: CityRepository
) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        try {
            Log.d("WORKER", "start")
            val reader = applicationContext.assets.open(JSON_CITY_FILE_NAME).bufferedReader()
            val jsonReader = JsonReader(reader)
            jsonReader.beginArray()
            val type = TypeToken.get(City::class.java).type
            while (jsonReader.hasNext()) {
                val city: City = Gson().fromJson(jsonReader, type)
                if(city.country == "RU") {
                    cityRepository.insertCity(city.toCityEntity())
                }

            }
            Log.d("WORKER", "end")
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): CitiesUploadWorker
    }

}