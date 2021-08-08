package com.weather.assignment.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.weather.assignment.R
import com.weather.assignment.listeners.ToolbarListener
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

class MainActivity : AppCompatActivity(), ToolbarListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setToolbarTitle(title: String) {
        toolbar.title = title
    }

    override fun setToolbarVisibility(value: Int) {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showError(error: String = "ERROR", duration: Int = Snackbar.LENGTH_SHORT) {
        val snackBar = Snackbar.make(
            findViewById(R.id.constraintLayoutParent),
            error, duration
        )
        snackBar.show()
    }
}
