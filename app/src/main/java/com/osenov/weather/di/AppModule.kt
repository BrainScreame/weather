package com.osenov.weather.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.osenov.weather.JSON_CITY_FILE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppBindsModule::class, DatabaseModule::class, ViewModelModule::class, ApiModule::class])
object AppModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideCityJsonReader(context : Context): JsonReader {
        return JsonReader(context.assets.open(JSON_CITY_FILE_NAME).bufferedReader())
    }

}