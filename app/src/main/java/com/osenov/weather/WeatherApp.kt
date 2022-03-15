package com.osenov.weather

import android.app.Application
import androidx.room.Room
import androidx.work.Configuration
import androidx.work.WorkManager
import com.osenov.weather.data.source.db.WeatherDatabase
import com.osenov.weather.di.AppComponent
import com.osenov.weather.di.DaggerAppComponent
import com.osenov.weather.di.DatabaseModule
import javax.inject.Inject

class WeatherApp : Application() {
    lateinit var appComponent: AppComponent

    @Inject
    lateinit var sampleWorkerFactory: SampleWorkerFactory

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .application(application = this)
            .build()

        appComponent.injectTo(this)

        super.onCreate()

        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(sampleWorkerFactory)
            .build()
        WorkManager.initialize(this, workManagerConfig)

    }
}