package com.osenov.weather.ui.main.weather_city.weather_days

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.osenov.weather.R
import com.osenov.weather.appComponent
import com.osenov.weather.data.entities.weather.DailyWeather
import com.osenov.weather.data.entities.weather.HourlyWeather
import com.osenov.weather.databinding.FragmentWeatherDayBinding
import com.osenov.weather.ui.main.weather_city.hour_list.HourlyItemDecoration
import com.osenov.weather.ui.main.weather_city.hour_list.HourlyRecyclerAdapter
import com.osenov.weather.utils.getIcon
import kotlin.math.roundToInt


class WeatherDayFragment : Fragment() {

    companion object {
        const val DAILY_WEATHER = "DAILY_WEATHER"
        const val HOURLY_WEATHER = "HOURLY_WEATHER"
    }

    private val hourlyRecyclerAdapter = HourlyRecyclerAdapter()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentWeatherDayBinding.inflate(layoutInflater)
    }

    private val viewModel: WeatherDayViewModel by viewModels {
        requireContext().appComponent.viewModelsFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dailyWeather: DailyWeather? = arguments?.getParcelable(DAILY_WEATHER)
        val hourlyWeather: ArrayList<HourlyWeather>? = arguments?.getParcelableArrayList(
            HOURLY_WEATHER
        )


        dailyWeather?.let {
            binding.textInfoWeather.text = it.weather[0].main

            binding.textTemperature.text =
                "${it.dailyTemperature.dayTemperature.roundToInt()}\u00B0"

            binding.imageViewWeather.setImageResource(getIcon(it.weather[0].icon))

            binding.humidity.text = "${it.humidity}%"
            binding.wind.text = "${it.windSpeed}m/s"
            binding.pressure.text = "${it.pressure}hPa"
        }

        hourlyWeather?.let {
            binding.hourlyRecyclerView.adapter = hourlyRecyclerAdapter
            val spacing = resources.getDimensionPixelSize(R.dimen.spacing_hour_recyclerView)
            binding.hourlyRecyclerView.addItemDecoration(HourlyItemDecoration(spacing))
            hourlyRecyclerAdapter.setHours(it)

        }


    }


}