package com.android.weatherforecast.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.weatherforecast.R
import com.android.weatherforecast.model.weatherdata.WeatherViewDataModel

class WeatherDataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var weatherViewDataList: List<WeatherViewDataModel> = emptyList()
    
    fun setWeatherData(weatherDataList: List<WeatherViewDataModel>) {
        Log.d(TAG, "setWeatherData")
        
        weatherViewDataList = weatherDataList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = weatherViewDataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")

        if (itemCount > position && position != RecyclerView.NO_POSITION) {
            val viewHolder = holder as WeatherDataViewHolder
            viewHolder.build(weatherViewDataList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder")

        return WeatherDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.weather_data_item,
                parent,
                false
            )
        )
    }

    inner class WeatherDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDate: TextView = view.findViewById(R.id.tvDate)
        private val tvAvgTemp: TextView = view.findViewById(R.id.tvAvgTemp)
        private val tvPressure: TextView = view.findViewById(R.id.tvPressure)
        private val tvHumidity: TextView = view.findViewById(R.id.tvHumidity)
        private val tvDescription: TextView = view.findViewById(R.id.tvDescription)

        fun build(weatherData: WeatherViewDataModel) {
            Log.d(TAG, "WeatherDataViewHolder - build")
    
            weatherData.apply {
                tvDate.apply { text = context.getString(R.string.date_text_holder, date) }
                tvAvgTemp.apply { text = context.getString(R.string.avg_temp_text_holder, avgTemp) }
                tvPressure.apply { text = context.getString(R.string.pressure_text_holder, pressure) }
                tvHumidity.apply { text = context.getString(R.string.humidity_text_holder, humidity) }
                tvDescription.apply { text = context.getString(R.string.description_text_holder, description) }
            }
        }
    }

    companion object {
        private val TAG = WeatherDataAdapter::class.java.simpleName
    }
}