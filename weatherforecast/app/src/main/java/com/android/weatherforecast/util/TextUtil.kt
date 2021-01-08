package com.android.weatherforecast.util

import com.android.weatherforecast.constants.APIConstants.MINIMUM_SEARCH_LENGTH

fun String.verifySearchText() : Boolean {
    return this.length >= MINIMUM_SEARCH_LENGTH
}