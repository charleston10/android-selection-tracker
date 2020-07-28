package com.charleston.selection_tracker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.charleston.selection_tracker.adapter.tracker.DetailsLookup
import com.charleston.selection_tracker.adapter.tracker.KeyProvider
import com.charleston.selection_tracker.adapter.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {

    private val listAdapter by lazy { ListAdapter() }
    private lateinit var selectionTracker: SelectionTracker<String>

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
        setupSelectionTracker()
        bindButton()
        addItems()
        initialize()
    }

    @ExperimentalStdlibApi
    override fun onResume() {
        super.onResume()

        getNavigationResult()?.value?.let {
            listScreen.removeLastOrNull()
            addItems()
        }

        if(listScreen.isNotEmpty()){
            listAdapter.select(listScreen.last().document)
        }
    }

    private fun setupList() {
        list.run {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupSelectionTracker(){
        selectionTracker = SelectionTracker.Builder(
            "id_selection_tracker",
            list,
            KeyProvider(
                listScreen
            ),
            DetailsLookup(list),
            StorageStrategy.createStringStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectSingleAnything()
        ).build()
    }


    private fun addItems() {
        listAdapter.addList(listScreen)
        Toast.makeText(context, "Total ${listScreen.size}", Toast.LENGTH_LONG).show()
    }

    private fun bindButton() {
        materialButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
    }

    private fun initialize(){
        listAdapter.setSelectionTracker(selectionTracker)
    }
}