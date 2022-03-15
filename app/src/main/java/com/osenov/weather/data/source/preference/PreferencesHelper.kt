package com.osenov.weather.data.source.preference

import android.content.Context
import android.content.SharedPreferences
import com.osenov.weather.CITIES_WORK_MANAGER_ID
import com.osenov.weather.PREF_FILE_NAME
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context) {

    private var preferences: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    private fun getEditor(): SharedPreferences.Editor {
        return preferences.edit()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun setCitiesWorkManagerId(citiesWorkManagerId: String) {
        getEditor().putString(CITIES_WORK_MANAGER_ID, citiesWorkManagerId).commit()
    }

    fun getCitiesWorkManagerId(): String? {
        return preferences.getString(CITIES_WORK_MANAGER_ID, "")
    }

}