package com.osenov.weather.ui.main.weather_city.cities_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.databinding.ItemFavoriteCityBinding
import com.osenov.weather.databinding.ItemHourWeatherBinding
import com.osenov.weather.ui.main.city_list.CityClickListener
import com.osenov.weather.ui.main.city_list.FavoriteCityClickListener
import com.osenov.weather.ui.main.weather_city.hour_list.HourViewHolder

class FavoriteCitiesRecyclerAdapter(
    private val citySelected: City?,
    private val favoriteCityClickListener: FavoriteCityClickListener
) : RecyclerView.Adapter<FavoriteCitiesViewHolder>() {

    private var favoriteCities = listOf<City>()

    fun setFavoriteCities(favoriteCities: List<City>) {
        this.favoriteCities = favoriteCities
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCitiesViewHolder {
        return FavoriteCitiesViewHolder(
            ItemFavoriteCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteCitiesViewHolder, position: Int) {
        holder.bind(favoriteCities[position], citySelected, favoriteCityClickListener.clickListener)
    }

    override fun getItemCount(): Int {
        return favoriteCities.size
    }
}