package com.osenov.weather.ui.main.city_list

import androidx.recyclerview.widget.RecyclerView
import com.osenov.weather.R
import com.osenov.weather.data.entities.city.City
import com.osenov.weather.databinding.ItemCityBinding
import java.util.*
import androidx.core.content.ContextCompat

class CityViewHolder(private val viewBinding: ItemCityBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    companion object{
        const val ADD_FAVORITE = "ADD_FAVORITE"
        const val DELETE_FAVORITE = "DELETE_FAVORITE"
    }

    fun bind(city: City?, cityClickListener: (City) -> Unit, viewModel: CityListViewModel) {
        viewBinding.textViewCityName.text = city?.name

        if (city != null) {
            val countryName = Locale(city.country).displayName
            if (city.state != "") {
                viewBinding.textViewCountry.text = "${city.state}, $countryName"
            } else {
                viewBinding.textViewCountry.text = countryName
            }
        }

        if(city?.favorite == false){
            viewBinding.imageViewAddCity.setImageResource(R.drawable.ic_baseline_add_24)
            viewBinding.imageViewAddCity.tag = ADD_FAVORITE
        } else {
            viewBinding.imageViewAddCity.setImageResource(R.drawable.ic_baseline_check_24)
            viewBinding.imageViewAddCity.tag = DELETE_FAVORITE
        }

        viewBinding.imageViewAddCity.setOnClickListener {
            if(viewBinding.imageViewAddCity.tag == ADD_FAVORITE) {
                viewBinding.imageViewAddCity.tag = DELETE_FAVORITE
                viewBinding.imageViewAddCity.setImageResource(R.drawable.ic_baseline_check_24)
                city?.favorite = true
                viewModel.updateCity(city!!)
            } else {
                viewBinding.imageViewAddCity.tag = ADD_FAVORITE
                viewBinding.imageViewAddCity.setImageResource(R.drawable.ic_baseline_add_24)
                city?.favorite = false
                viewModel.updateCity(city!!)
            }
        }

        viewBinding.root.setOnClickListener {
            if (city != null) {
                cityClickListener(city)
            }
        }
    }
}