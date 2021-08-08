package com.weather.assignment.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.weather.assignment.R
import com.weather.assignment.databinding.FragmentHomeBinding
import com.weather.assignment.databinding.FragmentWeatherdetailsBinding
import com.weather.assignment.model.WeatherDetails
import com.weather.assignment.ui.base.BaseFragment
import com.weather.assignment.utils.BundleConstants

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

class WeatherDetailsFragment : BaseFragment<FragmentHomeBinding, WeatherDetailsViewModel>(),
    LifecycleOwner {

    lateinit var weatherDetails: WeatherDetails
    lateinit var weatherDetailsViewModel: WeatherDetailsViewModel
    lateinit var cityName: String

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun setClickListener() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding: FragmentWeatherdetailsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_weatherdetails,
                container,
                false
            )

        val view = binding.root
        binding.weatherItemViewModel = getViewModel()
        binding.lifecycleOwner = this

        return view
    }

    override fun getViewModel(): WeatherDetailsViewModel {
        weatherDetailsViewModel = WeatherDetailsViewModel(
            weatherDetails,
            ""
        )// ViewModelProviders.of(this)[WeatherDetailsViewModel::class.java]
        return weatherDetailsViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            weatherDetails = it.getParcelable(BundleConstants.WEATHER_DETAILS)!!
        }

        weatherDetailsViewModel =
            WeatherDetailsViewModel(weatherDetails, "${weatherDetails.city} Weather Forecast")

    }

    override fun initialize() {

    }
}
