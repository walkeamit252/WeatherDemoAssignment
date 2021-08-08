package com.weather.assignment.ui.base

import androidx.lifecycle.ViewModel
import com.weather.assignment.network.ResponseCallBack


/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

open class BaseViewModel() : ViewModel(),
    ResponseCallBack<Any> {

    override fun onSuccess(value: Any) {

    }

    override fun onError(error: String) {

    }

}