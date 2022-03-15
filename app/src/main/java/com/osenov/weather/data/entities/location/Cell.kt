package com.osenov.weather.data.entities.location

data class Cell(
    val lac: Int,
    val cid: Int,
    val psc: Int? = null
)