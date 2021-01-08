package com.android.weatherforecast.model.weatherdata

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)