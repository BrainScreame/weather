package com.osenov.weather.ui.main.weather_city.hour_list

import androidx.recyclerview.widget.RecyclerView
import com.osenov.weather.data.entities.weather.HourlyWeather
import com.osenov.weather.data.entities.weather.WeatherApi
import com.osenov.weather.databinding.ItemHourWeatherBinding
import com.osenov.weather.utils.getIcon
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HourViewHolder(private val binding: ItemHourWeatherBinding) : RecyclerView.ViewHolder(binding.root) {

    private val dateFormat = SimpleDateFormat("HH:mm")

    fun bind(hourlyWeather: HourlyWeather) {

        binding.imageViewWeather.setImageResource(getIcon(hourlyWeather.weather[0].icon))
        binding.textTemperature.text = "${hourlyWeather.temperature.roundToInt()}\u00B0"
        binding.textHour.text = dateFormat.format(Date(hourlyWeather.time * 1000))
    }

}