package com.charleston.selection_tracker

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {

    private val listAdapter by lazy { ListAdapter() }

    private val listScreen =
        arrayListOf(
            ItemModel("017.277.790-96"),
            ItemModel("871.267.880-53"),
            ItemModel("604.285.540-91"),
            ItemModel("363.922.810-37"),
            ItemModel("699.291.530-47")
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()
        bindList()
        bindButton()
    }

    override fun onResume() {
        super.onResume()

        getNavigationResult()?.value?.let {
            listScreen.removeAt(it.toInt())
            bindList()
        }
    }

    private fun setupList() {
        list.run {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val tracker = SelectionTracker.Builder(
            "id_selection_tracker",
            list,
            KeyProvider(listScreen),
            DetailsLookup(list),
            StorageStrategy.createStringStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectSingleAnything()
        ).build()
    }

    private fun bindList() {
        listAdapter.addList(listScreen)
        Toast.makeText(context, "Total ${listScreen.size}", Toast.LENGTH_LONG).show()
    }

    private fun bindButton() {
        materialButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
    }

    class KeyProvider(private val list: List<ItemModel>) : ItemKeyProvider<String>(SCOPE_MAPPED) {

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

    class DetailsLookup(private val recyclerView: RecyclerView) :
        ItemDetailsLookup<String>() {
        override fun getItemDetails(event: MotionEvent): ItemDetails<String>? {
            val view = recyclerView.findChildViewUnder(event.x, event.y)

            if (view != null) {
                val holder = recyclerView.getChildViewHolder(view)
                if (holder is ListAdapter.ListViewHolder) {
                    //return holder.itemDetails
                }
            }
            return null
        }
    }

    class ItemModelLookup(
        private val itemModel: ItemModel,
        private val adapterPosition: Int = 0
    ) : ItemDetailsLookup.ItemDetails<String>() {
        override fun getPosition() = adapterPosition
        override fun getSelectionKey() = itemModel.document
        override fun inSelectionHotspot(e: MotionEvent) = true
    }
}