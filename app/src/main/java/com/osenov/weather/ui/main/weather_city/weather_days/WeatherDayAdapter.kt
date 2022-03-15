package com.osenov.weather.ui.main.weather_city.weather_days

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.osenov.weather.data.entities.weather.DailyWeather
import com.osenov.weather.data.entities.weather.HourlyWeather
import java.util.*
import kotlin.collections.ArrayList

class WeatherDayAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var dailyWeather = ArrayList<DailyWeather>()
    private var hourlyWeather = ArrayList<HourlyWeather>()

    fun setDaysWeather(
        dailyWeather: ArrayList<DailyWeather>,
        hourlyWeather: ArrayList<HourlyWeather>
    ) {
        this.dailyWeather = dailyWeather
        this.hourlyWeather = hourlyWeather
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dailyWeather.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = WeatherDayFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(WeatherDayFragment.DAILY_WEATHER, dailyWeather[position])
            val calendar = GregorianCalendar()
            val countHours = 24 - calendar.get(Calendar.HOUR_OF_DAY)
            val startHours = if (position == 0) 0 else (24 * position - (24 - countHours))

            if (startHours < hourlyWeather.size) {
                putParcelableArrayList(
                    WeatherDayFragment.HOURLY_WEATHER,
                    hourlyWeather.subList(
                        startHours,
                        if (countHours + 24 * position <= hourlyWeather.size)
                            countHours + 24 * position
                        else hourlyWeather.size
                    ).toCollection(ArrayList())
                )
            }
        }
        return fragment
    }

}