package com.weather.assignment.model

import androidx.databinding.BaseObservable

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */
data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherDetails>,
    val message: Double
) : BaseObservable()












