package com.osenov.weather.ui.main.weather_city

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.osenov.weather.appComponent
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.databinding.FragmentWeatherCityBinding
import com.osenov.weather.ui.main.weather_city.weather_days.WeatherDayAdapter
import java.text.SimpleDateFormat
import java.util.*

class WeatherCityFragment : Fragment() {

    companion object {
        const val cityModel = "CITY"
    }

    private var city: City? = null

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("EEE, dd MMM")

    private val viewModel: WeatherCityViewModel by viewModels {
        requireContext().appComponent.viewModelsFactory()
    }

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentWeatherCityBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherDayAdapter = WeatherDayAdapter(this)

        binding.viewPagerWeather.adapter = weatherDayAdapter

        city = arguments?.getParcelable(cityModel)

        if (city != null) {
            viewModel.getWeather(city!!)
        }

//        val favoriteCitiesAdapter = FavoriteCitiesRecyclerAdapter(city, FavoriteCityClickListener {
//            viewModel.getWeather(it)
//        })
//        binding.favoriteCitiesRecyclerView.adapter = favoriteCitiesAdapter
//
//        viewModel.getFavoriteCities()
//        viewModel.favoriteCitiesLiveData.observe(this, {
//            favoriteCitiesAdapter.setFavoriteCities(it)
//            favoriteCitiesAdapter.notifyDataSetChanged()
//        })




        viewModel.weatherLiveData.observe(this) {
            weatherDayAdapter.setDaysWeather(it.dailyWeather, it.hourlyWeather)
            TabLayoutMediator(binding.tabLayout, binding.viewPagerWeather) { tab, pos ->
                tab.text = dateFormat.format(Date(it.dailyWeather[pos].time * 1000))
            }.attach()
        }


//        binding.circleView.setPercentValue(100)
//        binding.circleView.percentAnimator()
//
//        binding.circleView.setOnClickListener{
//
//        }

    }

}