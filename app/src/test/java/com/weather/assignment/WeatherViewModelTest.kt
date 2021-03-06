package com.weather.assignment


/**
 * Created by Amit walke on 08/08/21.
 */


import android.content.Context
import com.weather.assignment.ui.home.HomeFragment
import com.weather.assignment.ui.home.HomeViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class WeatherViewModelTest {

    @Mock
    lateinit var weatherFragment: HomeFragment

    @Mock
    lateinit var weatherViewModel: HomeViewModel

    @Mock
    lateinit var context: Context

    @Before
    @Throws(Exception::class)
    fun setUp() {
        weatherFragment = HomeFragment()
    }

    @Test
    fun onSearchButtonClickTest() {
        weatherViewModel.checkNetworkAndProcessd(context)
        verify<HomeFragment>(weatherFragment, only()).searchedCityName
    }

    @Test
    fun nullifyParameters_Invoked() {
        weatherViewModel.nullifyParameters()
        assertEquals(null, weatherViewModel.errorMessage)
        assertEquals(null, weatherViewModel.progressStatus)
        assertEquals(null, weatherViewModel.cityName)
    }

    @Test
    fun citynameValidation_Test() {
        assertTrue("Invalid City Name", weatherViewModel.isCityNameValid(""))
        assertTrue("Invalid City Name", weatherViewModel.isCityNameValid("12"))
        assertTrue("Valid City Name ", weatherViewModel.isCityNameValid("Bhubaneswar"))
    }
}