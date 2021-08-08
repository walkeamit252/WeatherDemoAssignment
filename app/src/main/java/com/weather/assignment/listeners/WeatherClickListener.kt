package com.weather.assignment.listeners

import com.weather.assignment.model.WeatherDetails


interface WeatherClickListener {
    fun onWeatherClick(weatherDetails: WeatherDetails)
}