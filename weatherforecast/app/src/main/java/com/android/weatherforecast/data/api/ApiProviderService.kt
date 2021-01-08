package com.android.weatherforecast.data.api

import android.content.Context
import com.android.weatherforecast.builder.SingletonBuilder
import com.android.weatherforecast.constants.APIConstants.CACHE_SIZE
import com.android.weatherforecast.constants.APIConstants.LAST_ONLINE_CACHE
import com.android.weatherforecast.constants.APIConstants.MAXIMUM_CACHE_AGE
import com.android.weatherforecast.constants.APIConstants.WEATHER_API_URL
import com.android.weatherforecast.util.isNetworkAvailable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiProviderService(context: Context) {
    private val converterFactory = GsonConverterFactory.create()
    private val interceptor = Interceptor {
        var request = it.request()
        request = request.newBuilder().header("Cache-Control", if (context.isNetworkAvailable())
            "public, max-age=$LAST_ONLINE_CACHE"
        else
            "public, only-if-cached, max-stale=$MAXIMUM_CACHE_AGE"
        ).build()
    
        it.proceed(request)
    }
    
    private val cache = Cache(context.cacheDir, CACHE_SIZE)
    private val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        cache(cache)
    }.build()
    
    private val retrofit = Retrofit.Builder().apply {
        baseUrl(WEATHER_API_URL)
        client(okHttpClient)
        addConverterFactory(converterFactory)
    }.build()
    
    fun <T> createService(cls: Class<T>): T = retrofit.create(cls)
    
    companion object : SingletonBuilder<ApiProviderService, Context>({ ApiProviderService(it) })
}