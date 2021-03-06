package com.weather.assignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */
@Parcelize
data class Main(
    val grnd_level: Double,
    val humidity: Int,
    val pressure: Double,
    val sea_level: Double,
    val temp: Double,
    /*val temp_kf: Int,*/
    val temp_max: Double,
    val temp_min: Double
) : Parcelable