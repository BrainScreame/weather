package com.osenov.weather.ui.main.weather_city.cities_list

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.databinding.ItemFavoriteCityBinding

class FavoriteCitiesViewHolder(private val binding: ItemFavoriteCityBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(city: City, citySelected: City?, favoriteCityClickListener: (City) -> Unit) {
        if (city.id == citySelected?.id) {
            binding.constraintLayoutFavoriteCityItem.setBackgroundColor(Color.CYAN)
        }
        binding.cityName.text = city.name

        binding.root.setOnClickListener {
            binding.constraintLayoutFavoriteCityItem.setBackgroundColor(Color.CYAN)
            favoriteCityClickListener(city)
        }
    }
}