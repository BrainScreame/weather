package com.osenov.weather.ui.main.weather_city.hour_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osenov.weather.data.entities.weather.HourlyWeather
import com.osenov.weather.databinding.ItemHourWeatherBinding

class HourlyRecyclerAdapter : RecyclerView.Adapter<HourViewHolder>() {

    private var hourList = ArrayList<HourlyWeather>()

    fun setHours(hourList: ArrayList<HourlyWeather>) {
        this.hourList = hourList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        return HourViewHolder(
            ItemHourWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        holder.bind(hourList[position])
    }

    override fun getItemCount(): Int {
        return hourList.size
    }
}