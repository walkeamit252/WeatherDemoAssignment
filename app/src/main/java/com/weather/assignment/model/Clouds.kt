package com.weather.assignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

@Parcelize
data class Clouds(
    val all: Int
) : Parcelable