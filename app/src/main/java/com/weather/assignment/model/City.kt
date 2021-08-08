package com.weather.assignment.model

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)