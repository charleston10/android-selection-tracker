package com.charleston.selection_tracker.adapter.tracker

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.charleston.selection_tracker.adapter.ListViewHolder

class DetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<String>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<String>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)

        if (view != null) {
            val holder = recyclerView.getChildViewHolder(view)
            if (holder is ListViewHolder) {
                return holder.getItemLookup()
            }
        }
        return null
    }
}