package com.weather.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.weather.assignment.listeners.WeatherClickListener
import com.weather.assignment.model.WeatherDetails
import com.weather.assignment.ui.adapter.viewholders.WeatherViewHolder


class WeatherAdapter(var listWeather: ArrayList<WeatherDetails>) :
    RecyclerView.Adapter<WeatherViewHolder>() {


    lateinit var mWeatherClickListener: WeatherClickListener

    var data: MutableLiveData<ArrayList<WeatherDetails>> = MutableLiveData()

    init {
        data.value = listWeather
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        data.value?.let {
            val weatherItemViewModel = WeatherItemViewModel(it[position])
            holder.bind(weatherItemViewModel)

            holder.itemView.setOnClickListener {
                mWeatherClickListener.onWeatherClick(listWeather.get(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ItemviewWeatherBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.itemview_weather, parent, false)
        DataBindingUtil.getDefaultComponent()
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int = data.value!!.size

    fun updateData(@Nullable data: ArrayList<WeatherDetails>?) {
        if (data == null || data.isEmpty()) {
            this.data.value.let {
                this.data.value = ArrayList()
            }
        } else {
            this.data.value = data
        }
        notifyDataSetChanged()
    }

    fun updateWeatherListItems(newWeatherList: List<WeatherDetails>) {
        val diffCallback = WeatherDiffUtilsCallBack(this.listWeather, newWeatherList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listWeather.clear()
        this.listWeather.addAll(newWeatherList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setWeatherClcik(weatherClickListener: WeatherClickListener) {
        this.mWeatherClickListener = weatherClickListener
    }

}