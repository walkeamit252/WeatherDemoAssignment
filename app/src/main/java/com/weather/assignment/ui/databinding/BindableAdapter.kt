package com.weather.assignment.ui.databinding

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

interface BindableAdapter<T> {
    fun setData(items: List<T>)
    fun changedPositions(positions: Set<Int>)
}