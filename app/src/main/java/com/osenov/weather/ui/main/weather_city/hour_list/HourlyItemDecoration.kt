package com.osenov.weather.ui.main.weather_city.hour_list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HourlyItemDecoration(private val space : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.top = space
        outRect.bottom = space
    }

}