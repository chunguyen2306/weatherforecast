package com.android.weatherforecast.model.weatherdata

data class WeatherDataModel(
    val city: CityInfo,
    val cnt: Int,
    val cod: String,
    val list: List<DayWeather>,
    val message: Double
)