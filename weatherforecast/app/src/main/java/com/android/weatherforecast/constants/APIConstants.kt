package com.android.weatherforecast.constants

import com.android.weatherforecast.constants.DataSizeConstants.KB_IN_BYTES
import com.android.weatherforecast.constants.DataSizeConstants.MB_IN_BYTES
import com.android.weatherforecast.constants.TimeConstants.ONE_DAY_IN_SECONDS

object APIConstants  {
    const val WEATHER_API_URL = "https://api.openweathermap.org"
    const val DEFAULT_WEATHER_SAMPLE_SIZE = 7
    const val DEFAULT_APP_WEATHER_ID = "60c6fbeb4b93ac653c492ba806fc346d"
    const val MINIMUM_SEARCH_LENGTH = 3
    
    const val LAST_ONLINE_CACHE = 30
    const val MAXIMUM_CACHE_AGE = ONE_DAY_IN_SECONDS
    const val CACHE_SIZE = 5L * MB_IN_BYTES * KB_IN_BYTES
}