package com.android.weatherforecast.data.api

import com.android.weatherforecast.model.weatherdata.WeatherDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/data/2.5/forecast/daily?")
    suspend fun getWeatherData(
        @Query("q") cityName: String,
        @Query("cnt") sampleCount: Int,
        @Query("appid") appId: String,
        @Query("units") unit: String
    ): Response<WeatherDataModel>
}
