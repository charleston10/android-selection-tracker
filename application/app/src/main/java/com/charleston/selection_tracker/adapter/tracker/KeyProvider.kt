package com.charleston.selection_tracker.adapter.tracker

import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.widget.RecyclerView
import com.charleston.selection_tracker.ItemModel

class KeyProvider(
    private val list: List<ItemModel>
) : ItemKeyProvider<String>(SCOPE_MAPPED) {

    override fun getKey(position: Int) = list[position].document

    override fun getPosition(key: String): Int {
        return try {
            list.indexOf(
                list.single { item ->
                    item.document == key
                }
            )
        } catch (e: Exception) {
            RecyclerView.NO_POSITION
        }
    }
}