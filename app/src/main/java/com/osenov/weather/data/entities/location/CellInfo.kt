package com.osenov.weather.data.entities.location

import com.osenov.weather.BuildConfig
import com.osenov.weather.OPENCELLID_API_KEY

data class CellInfo(
    val token: String = OPENCELLID_API_KEY,
    var radio: String? = null,
    var mcc: Int? = null,
    var mnc: Int? = null,
    var cells: List<Cell> = emptyList(),
    val address: Int = 1
)