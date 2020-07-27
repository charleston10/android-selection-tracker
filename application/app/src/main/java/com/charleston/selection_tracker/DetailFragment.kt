package com.charleston.selection_tracker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class DetailFragment : Fragment(R.layout.fragment_detail){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavigationResult("4")
    }
}