package com.weather.assignment.ui.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weather.assignment.model.WeatherDetails
import com.weather.assignment.ui.adapter.WeatherAdapter

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

@BindingAdapter("adapterData")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(items)
    }
}


@BindingAdapter("changedPositions")
fun <T> setDataChanged(recyclerView: RecyclerView, positions: Set<Int>) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).changedPositions(positions)
    }

    @BindingAdapter("app:adapter")
    fun bind(recyclerView: RecyclerView, adapter: WeatherAdapter, data: ArrayList<WeatherDetails>) {
        recyclerView.setAdapter(adapter)
        adapter.updateData(data)
    }
}
