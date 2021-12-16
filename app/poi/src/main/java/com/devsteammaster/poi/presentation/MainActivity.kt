package com.devsteammaster.poi.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.devsteammaster.poi.R
import com.devsteammaster.poi.model.ListOfPlacesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListOfPlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostController =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        val navController = navHostController.navController

        viewModel = ViewModelProvider(this).get(ListOfPlacesViewModel::class.java)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.getListOfPlaces().observe(this, {
            Log.d("List Of Places in Main", it.toString())
        })
    }
}