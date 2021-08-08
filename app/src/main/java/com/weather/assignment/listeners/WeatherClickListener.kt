package com.weather.assignment.listeners

import com.weather.assignment.model.WeatherDetails

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

interface WeatherClickListener {
    fun onWeatherClick(weatherDetails: WeatherDetails)
}