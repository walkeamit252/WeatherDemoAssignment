package com.weather.assignment.ui.adapter.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.weather.assignment.ui.adapter.WeatherItemViewModel

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

class WeatherViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(weather: WeatherItemViewModel) {
        // binding.setVariable(BR.weatherViewModel, data)
        binding.setVariable(BR.weatherItemViewModel, weather)
        binding.executePendingBindings()
    }
}