package com.osenov.weather.utils

import com.osenov.weather.R

fun getIcon(iconId : String) : Int {
    return when(iconId) {
        "01d" -> R.drawable.ic_clear_sky_day
        "01n" -> R.drawable.ic_clear_sky_night
        "02d" -> R.drawable.ic_few_clouds_day
        "02n" -> R.drawable.ic_few_clouds_night
        "03d" -> R.drawable.ic_scattered_clouds
        "03n" -> R.drawable.ic_scattered_clouds
        "04d" -> R.drawable.ic_broken_clouds
        "04n" -> R.drawable.ic_broken_clouds
        "09d" -> R.drawable.ic_shower_rain
        "09n" -> R.drawable.ic_shower_rain
        "10d" -> R.drawable.ic_rain_day
        "10n" -> R.drawable.ic_rain_night
        "11d" -> R.drawable.ic_thunderstorm
        "11n" -> R.drawable.ic_thunderstorm
        "13d" -> R.drawable.ic_snow_day
        "13n" -> R.drawable.ic_snow_day
        "50d" -> R.drawable.ic_mist
        "50n" -> R.drawable.ic_mist
        else -> R.drawable.ic_clear_sky_day
    }
}