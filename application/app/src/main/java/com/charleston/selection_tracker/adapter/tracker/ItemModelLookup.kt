package com.charleston.selection_tracker.adapter.tracker

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import com.charleston.selection_tracker.ItemModel

class ItemModelLookup(
    var itemModel: ItemModel? = null,
    var adapterPosition: Int = 0
) : ItemDetailsLookup.ItemDetails<String>() {

    override fun getPosition() = adapterPosition
    override fun getSelectionKey() = itemModel?.document
    override fun inSelectionHotspot(e: MotionEvent) = true
}