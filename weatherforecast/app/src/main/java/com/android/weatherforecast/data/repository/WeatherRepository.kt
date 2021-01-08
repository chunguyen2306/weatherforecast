package com.android.weatherforecast.data.repository

import android.content.Context
import android.util.Log
import com.android.weatherforecast.builder.SingletonBuilder
import com.android.weatherforecast.data.api.ApiProviderService
import com.android.weatherforecast.data.api.WeatherApiService
import com.android.weatherforecast.model.weatherdata.WeatherDataModel
import retrofit2.Response

class WeatherRepository(context: Context) {
    private var weatherApiService: WeatherApiService = ApiProviderService.getInstance(context).createService(WeatherApiService::class.java)
    
    suspend fun fetchWeatherData(cityName: String, sampleCount: Int, appId: String, unit: String): Response<WeatherDataModel> {
        Log.d(TAG, "fetchWeatherData")

        return weatherApiService.getWeatherData(cityName, sampleCount, appId, unit)
    }
    
    companion object : SingletonBuilder<WeatherRepository, Context>({ WeatherRepository(it) }) {
        private val TAG = WeatherRepository::class.java.simpleName
    }
}