package com.android.weatherforecast.util

import android.text.format.DateFormat
import com.android.weatherforecast.constants.TimeConstants.DATE_FORMAT_PATTERN
import com.android.weatherforecast.constants.TimeConstants.ONE_SECOND_IN_MILLIS
import com.android.weatherforecast.model.weatherdata.WeatherDataModel
import com.android.weatherforecast.model.weatherdata.WeatherViewDataModel
import java.util.Date
import java.util.Locale

fun WeatherDataModel?.convertWeatherDataViewModel() : List<WeatherViewDataModel>? {
    val resultList = mutableListOf<WeatherViewDataModel>()
    
    this?.list?.forEach { day ->
        val dateInMillis = day.dt * ONE_SECOND_IN_MILLIS
        val date = Date(dateInMillis)
        val formattedDateStr = DateFormat.format(DATE_FORMAT_PATTERN, date).toString()
        val avgTempStr = day.temp.let {
            val avgTemp = calculateAvgTemperature(it.day, it.eve, it.morn, it.night)
            val unit = Locale.getDefault().getLocaleTemperatureUnit().unit
            
            "$avgTemp $unit"
        }
        val pressureStr = day.pressure.toString()
        val humidityStr = day.humidity.toString()
        val descriptionStr = if (day.weather.isEmpty()) "" else day.weather[0].description
        
        resultList.add(WeatherViewDataModel(
                date = formattedDateStr,
                avgTemp = avgTempStr,
                pressure = pressureStr,
                humidity = humidityStr,
                description = descriptionStr
        ))
    }
    
    return resultList
}

private fun calculateAvgTemperature(vararg temperature: Double): Int = temperature.average().toInt()