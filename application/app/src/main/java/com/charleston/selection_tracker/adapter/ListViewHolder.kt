package com.charleston.selection_tracker.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.charleston.selection_tracker.ItemModel
import com.charleston.selection_tracker.R
import com.charleston.selection_tracker.adapter.tracker.ItemModelLookup
import com.google.android.material.checkbox.MaterialCheckBox

class ListViewHolder(
    itemView: View,
    private val selectionTracker: SelectionTracker<String>?
) : RecyclerView.ViewHolder(itemView) {

    private var itemLookup = ItemModelLookup()

    fun bind(item: ItemModel) {
        bindItemLookup(item, adapterPosition)
        bindView(item)
    }

    fun getItemLookup(): ItemModelLookup {
        return itemLookup
    }

    private fun bindItemLookup(item: ItemModel, position: Int) {
        itemLookup.let {
            it.itemModel = item
            it.adapterPosition = position
        }
    }

    private fun bindView(item: ItemModel) {
        itemView.findViewById<TextView>(R.id.txt_document).text = item.document
        itemView.findViewById<MaterialCheckBox>(R.id.materialCheckBox).isChecked =
            selectionTracker?.isSelected(item.document) ?: false
    }
}