package com.android.weatherforecast.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.weatherforecast.R
import com.android.weatherforecast.constants.APIConstants.DEFAULT_APP_WEATHER_ID
import com.android.weatherforecast.constants.APIConstants.DEFAULT_WEATHER_SAMPLE_SIZE
import com.android.weatherforecast.data.repository.WeatherRepository
import com.android.weatherforecast.model.weatherdata.WeatherViewDataModel
import com.android.weatherforecast.ui.adapter.WeatherDataAdapter
import com.android.weatherforecast.ui.viewmodel.WeatherDataViewModel
import com.android.weatherforecast.util.convertWeatherDataViewModel
import com.android.weatherforecast.util.getLocaleTemperatureUnit
import com.android.weatherforecast.util.verifySearchText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {
    
    private lateinit var etCityInput: EditText
    private lateinit var btnGetWeather: Button
    private lateinit var tvResultState: TextView
    private lateinit var rvWeatherData: RecyclerView
    private lateinit var weatherDataViewModel: WeatherDataViewModel
    private lateinit var weatherRepository: WeatherRepository
    private val fetchDataScope = CoroutineScope(Dispatchers.IO)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val weatherDataAdapter: WeatherDataAdapter = WeatherDataAdapter()
    private val weatherDataObserver = Observer<List<WeatherViewDataModel>> {
        weatherDataAdapter.setWeatherData(it)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_main)
    
        initViews()
    
        weatherDataViewModel = ViewModelProvider(this@MainActivity).get(WeatherDataViewModel::class.java)
        weatherDataViewModel.getWeatherLiveData().observe(this@MainActivity, weatherDataObserver)
        
        weatherRepository = WeatherRepository.getInstance(this@MainActivity)
    }

    private fun initViews() {
        Log.d(TAG, "initViews")

        etCityInput = findViewById(R.id.etCityInput)

        btnGetWeather = findViewById(R.id.btnGetWeather)
        btnGetWeather.setOnClickListener(this)
    
        tvResultState = findViewById(R.id.tvResultState)

        rvWeatherData = findViewById(R.id.rvWeatherData)
        rvWeatherData.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
            adapter = weatherDataAdapter
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnGetWeather -> {
                Log.d(TAG, "onClick - btnGetWeather")
                val cityNameStr = etCityInput.text.toString()
                val temperatureUnitStr = Locale.getDefault().getLocaleTemperatureUnit().name
                
                if (cityNameStr.verifySearchText()) {
                    fetchDataScope.launch {
                        val weatherDataResponse = weatherRepository.fetchWeatherData(
                            cityNameStr,
                            DEFAULT_WEATHER_SAMPLE_SIZE,
                            DEFAULT_APP_WEATHER_ID,
                            temperatureUnitStr
                        )
                        
                        if (weatherDataResponse.isSuccessful) {
                            val weatherData = weatherDataResponse.body()
                            
                            weatherData.apply {
                                weatherDataViewModel.updateWeatherLiveData(weatherData.convertWeatherDataViewModel())
                                showResultTextView(false)
                            } ?: run {
                                onResultError(getString(R.string.weather_data_error))
                            }
                        } else {
                            onResultError(getString(R.string.weather_data_error))
                        }
                    }
                } else {
                    onResultError(getString(R.string.weather_data_invalid_search_error))
                }
            }
        }
    }

    private fun onResultError(errorStr: String) {
        weatherDataViewModel.updateWeatherLiveData(emptyList())
        showResultTextView(true, errorStr)
    }
    
    private fun showResultTextView(isShow: Boolean, message: String = "") {
        mainScope.launch {
            tvResultState.apply {
                text = message
                visibility = if (isShow) View.VISIBLE else View.GONE
            }
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}