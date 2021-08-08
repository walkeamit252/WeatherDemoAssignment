package com.weather.assignment.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.weather.assignment.R
import com.weather.assignment.databinding.FragmentHomeBinding
import com.weather.assignment.listeners.WeatherClickListener
import com.weather.assignment.model.WeatherDetails
import com.weather.assignment.ui.activity.MainActivity
import com.weather.assignment.ui.adapter.WeatherAdapter
import com.weather.assignment.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), LifecycleOwner,
    WeatherClickListener {

    var weatherList: ArrayList<WeatherDetails> = ArrayList()

    var searchedCityName = ""

    val weatherAdapter: WeatherAdapter by lazy {
        WeatherAdapter(weatherList)
    }

    lateinit var weatherViewModel: HomeViewModel


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun setClickListener() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding: FragmentHomeBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
            )

        val view = binding.root
        binding.weatherViewmodel = getViewModel()
        binding.lifecycleOwner = this

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setAdapter()
        setupSwipeRefresh()
        setObservData()
    }

    override fun getViewModel(): HomeViewModel {
        weatherViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        return weatherViewModel
    }

    fun hideProgress() {
        //swipeContainer.isRefreshing = false
        hideProgressBar()
    }

    /**
     * Showing Error
     */
    fun showError(error: String) {
        hideProgress()
        when (error) {
            context?.getString(R.string.err_nointernet) -> {
                (activity as MainActivity).showError(
                    getString(R.string.err_nointernet),
                    Snackbar.LENGTH_INDEFINITE
                )
            }
            context?.getString(R.string.err_invalid_city_name) -> {
                hideError()
                (activity as MainActivity).showError(
                    getString(R.string.err_invalid_city_name),
                    Snackbar.LENGTH_INDEFINITE
                )
            }
            else -> {
                if (error.isEmpty()) {
                    hideError()
                } else {
                    showErrorBase(error)
                }
            }
        }
    }

    /**
     * Initialize and setup for ui elements
     */
    override fun initialize() {

        Handler().postDelayed(Runnable { hideProgress() }, 3000)


        searchedCityName = autocompleteTextViewWeather.text.toString()
        (activity as MainActivity).showError("London Weather Forecat")
        autocompleteTextViewWeather.setOnItemClickListener { adapterView, view, i, l ->
            searchedCityName = autocompleteTextViewWeather.text.toString()
            (activity as MainActivity).setToolbarTitle(searchedCityName)
        }

        if (getViewModel().mutableWeatherList.value != null && getViewModel().mutableWeatherList.value?.size != 0) {
            getViewModel().mutableWeatherList.value?.let { weatherAdapter.updateWeatherListItems(it) }
        }

    }

    /**
     * SET up UI for Pull to refresh
     */
    fun setupSwipeRefresh() {

        swipeContainer.setOnRefreshListener {
            getViewModel().searchCityWeather(activity as FragmentActivity, searchedCityName)
        }

        swipeContainer.setColorSchemeColors(
            ContextCompat.getColor(activity as FragmentActivity, android.R.color.holo_blue_bright),
            ContextCompat.getColor(activity as FragmentActivity, android.R.color.holo_green_light),
            ContextCompat.getColor(activity as FragmentActivity, android.R.color.holo_orange_light),
            ContextCompat.getColor(activity as FragmentActivity, android.R.color.holo_red_light)
        )
    }

    /**
     * SETup weather recyclerview Adapter
     */
    fun setAdapter() {
        weatherAdapter.setWeatherClcik(this)
        recyclerView.adapter = weatherAdapter
        val countries = resources.getStringArray(R.array.india_top_cities)
        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, countries)
        autocompleteTextViewWeather.setAdapter(adapter)
    }

    /**
     * set all the observeable Livedata in view model
     */
    fun setObservData() {
        getViewModel().fetchCityWeatherListFromServer(activity as FragmentActivity)
            .observe(viewLifecycleOwner, Observer {
                weatherAdapter.updateWeatherListItems(it)
                showError("")
            })

        getViewModel().getProgressStatus()?.observe(viewLifecycleOwner, Observer {
            if (it) showProgressBar() else hideProgress()
        })

        getViewModel().getErrorStatus()?.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) showError(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        getViewModel().nullifyParameters()
    }

    override fun onWeatherClick(weatherDetails: WeatherDetails) {
        showWeatherDetailsFragment(weatherDetails)
    }

    private fun showWeatherDetailsFragment(weatherDetails: WeatherDetails) {
        weatherDetails.city = searchedCityName
        val actionToWeatherDetails =
            HomeFragmentDirections.actionHomeFragmentToDetailsWeatherFragment(weatherDetails)
        findNavController().navigate(actionToWeatherDetails)
    }
}
