package com.weather.assignment.ui.home

import android.util.Log
import com.weather.assignment.BuildConfig
import com.weather.assignment.model.WeatherResponse
import com.weather.assignment.network.ApiServiceInterface
import com.weather.assignment.network.ResponseCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

/**
 * Model to make the api call
 */
class WeatherRepository {

    fun getWeatherListForCity(cityName: String, callback: ResponseCallBack<WeatherResponse>) {

        /**
         * NOW GET THE DETAILS FROM API
         */
        val call = ApiServiceInterface.create().getWeatherDetails(cityName, BuildConfig.API_KEY)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                var res = response.body()?.let {
                    if (it.cod == "200") {
                        callback.onSuccess(it)
                    } else {
                        callback.onError("Server Error")
                    }
                    1
                }

                if (res != 1) {
                    callback.onError("Error")
                }

            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.i("ERROR", call.toString())
                callback.onError(call.toString())
            }
        })
    }
}