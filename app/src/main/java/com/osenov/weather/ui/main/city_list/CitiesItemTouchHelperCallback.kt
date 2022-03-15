package com.osenov.weather.ui.main.city_list

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CitiesItemTouchHelperCallback(private val adapter: CitiesPagingAdapter) :
    ItemTouchHelper.SimpleCallback(UP or DOWN, 0) {

    override fun isLongPressDragEnabled() : Boolean {
        return true
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.notifyItemMoved(viewHolder.absoluteAdapterPosition, target.absoluteAdapterPosition)
        //Collections.swap(adapter.submitData, viewHolder.absoluteAdapterPosition, target.absoluteAdapterPosition)
        return  true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //nothing()
    }



}