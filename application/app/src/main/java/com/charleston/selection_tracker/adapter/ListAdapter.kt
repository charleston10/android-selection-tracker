package com.charleston.selection_tracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.charleston.selection_tracker.ItemModel
import com.charleston.selection_tracker.R

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: ArrayList<ItemModel> = arrayListOf()
    private var selectionTracker: SelectionTracker<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list, parent, false),
            selectionTracker
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as? ListViewHolder) {
            this?.bind(list[position])
        }
    }

    fun addList(list: List<ItemModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setSelectionTracker(selectionTracker: SelectionTracker<String>) {
        this.selectionTracker = selectionTracker;
    }

    fun select(document: String) {
        selectionTracker?.select(document)
    }
}