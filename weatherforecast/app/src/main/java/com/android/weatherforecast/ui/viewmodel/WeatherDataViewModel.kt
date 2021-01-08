package com.android.weatherforecast.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.android.weatherforecast.model.weatherdata.WeatherViewDataModel

class WeatherDataViewModel : ViewModel() {
    private val weatherLiveData: MutableLiveData<List<WeatherViewDataModel>> = MutableLiveData<List<WeatherViewDataModel>>()
    
    fun getWeatherLiveData(): LiveData<List<WeatherViewDataModel>> {
        Log.d(TAG, "updateWeatherLiveData")
    
        return weatherLiveData
    }

    fun updateWeatherLiveData(weatherData: List<WeatherViewDataModel>?) {
        Log.d(TAG, "updateWeatherLiveData")
        
        weatherLiveData.postValue(weatherData)
    }
    
    companion object {
        private val TAG = WeatherViewDataModel::class.java.simpleName
    }
}