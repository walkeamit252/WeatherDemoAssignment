package com.weather.assignment.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.weather.assignment.BR
import kotlinx.android.parcel.Parcelize

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

@Parcelize
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) : BaseObservable(), Parcelable {

    @get:Bindable
    var firstName: String = "SIba"
        set(value) {
            field = main
            notifyPropertyChanged(BR.firstName)
        }
}