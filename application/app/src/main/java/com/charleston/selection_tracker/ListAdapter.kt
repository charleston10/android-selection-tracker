package com.charleston.selection_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: ArrayList<Int> = arrayListOf()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //nothing bind
    }

    fun addList(list: List<Int>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}