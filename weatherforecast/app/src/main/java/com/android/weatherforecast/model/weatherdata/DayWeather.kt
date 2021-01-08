package com.android.weatherforecast.model.weatherdata

data class DayWeather (
    val clouds: Int,
    val deg: Int,
    val dt: Long,
    val feels_like: WeatherFeels,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temperature,
    val weather: List<Weather>
)