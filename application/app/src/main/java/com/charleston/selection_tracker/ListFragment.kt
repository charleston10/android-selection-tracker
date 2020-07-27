package com.charleston.selection_tracker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {

    private val listAdapter by lazy { ListAdapter() }
    private val listScreen = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()
        bindList()
        bindButton()
    }

    override fun onResume() {
        super.onResume()

        getNavigationResult()?.value?.let {
            listScreen.find { item -> item == it.toInt() }?.let {
                listScreen.remove(it)
                bindList()
            }
        }
    }

    private fun setupList() {
        list.run {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }
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
}