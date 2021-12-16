package com.devsteammaster.poi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devsteammaster.poi.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val gotoListButton =
            view.findViewById<ExtendedFloatingActionButton>(R.id.float_goto_list_button)

        gotoListButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listFragment)
        }
    }
}