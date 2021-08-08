package com.weather.assignment.network

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

public interface ResponseCallBack<S> {
    abstract fun onSuccess(value: S)
    abstract fun onError(error: String)
}