package com.osenov.weather

import android.content.Context
import java.io.IOException
import java.io.Reader


fun getJsonDataFromAsset(context: Context, fileName: String): Reader? {
    val reader: Reader
    try {
        reader = context.assets.open(fileName).bufferedReader()
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return reader
}
