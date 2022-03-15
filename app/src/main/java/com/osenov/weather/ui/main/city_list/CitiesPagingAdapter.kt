package com.osenov.weather.ui.main.city_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.databinding.ItemCityBinding

class CitiesPagingAdapter(
    private val viewModel: CityListViewModel,
    private val cityClickListener: CityClickListener
) :
    PagingDataAdapter<City, CityViewHolder>(CityDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position), cityClickListener.clickListener, viewModel)
    }

}


private object CityDiffItemCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }

}