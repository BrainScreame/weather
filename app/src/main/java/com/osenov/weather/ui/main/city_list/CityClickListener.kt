package com.osenov.weather.ui.main.city_list

import com.osenov.weather.data.entities.city.City

data class CityClickListener(val clickListener: (city: City) -> Unit)
data class FavoriteCityClickListener(val clickListener: (city: City) -> Unit)
