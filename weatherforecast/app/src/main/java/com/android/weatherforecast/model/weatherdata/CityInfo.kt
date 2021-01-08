package com.android.weatherforecast.model.weatherdata

data class CityInfo(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)