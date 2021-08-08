package com.weather.assignment.utils

import androidx.recyclerview.widget.DiffUtil
import com.weather.assignment.model.WeatherDetails

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

class WeatherDiffUtilsCallBack(
    val oldWeatherDetailsList: List<WeatherDetails>,
    val newWeatherDetailsList: List<WeatherDetails>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldWeatherDetailsList[oldItemPosition].dt == newWeatherDetailsList[newItemPosition].dt
    }

    override fun getOldListSize(): Int {
        return oldWeatherDetailsList.size
    }

    override fun getNewListSize(): Int {
        return newWeatherDetailsList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldWeather: WeatherDetails = oldWeatherDetailsList.get(oldItemPosition)
        val newWeather: WeatherDetails = newWeatherDetailsList.get(newItemPosition)

        return oldWeather.dt_txt === newWeather.dt_txt
    }
}