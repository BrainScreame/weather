package com.osenov.weather

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject

class SampleWorkerFactory @Inject constructor(
    private val citiesUploadWorkerFactory: CitiesUploadWorker.Factory,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): CoroutineWorker? {
        return when (workerClassName) {
            CitiesUploadWorker::class.java.name ->
                citiesUploadWorkerFactory.create(appContext, workerParameters)
            else -> null
        }
    }
}