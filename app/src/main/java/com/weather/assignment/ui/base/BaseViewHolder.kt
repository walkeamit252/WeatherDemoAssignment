package com.weather.assignment.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Amit Walke on 08/08/21.
 * walkeamit252@gmail.com
 */

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int)
}