package com.charleston.selection_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: ArrayList<ItemModel> = arrayListOf()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ItemModel) {
            itemView.findViewById<TextView>(R.id.txt_document).text = item.document
        }
    }

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
        with(holder as? ListViewHolder) {
            this?.bind(list[position])
        }
    }

    fun addList(list: List<ItemModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}