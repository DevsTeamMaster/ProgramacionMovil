package com.mintic.minticapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var name = intent.getStringExtra("listOfPlaces_extra_name")
        var description = intent.getStringExtra("listOfPlaces_extra_description")
        var image = intent.getStringExtra("listOfPlaces_extra_image")
        Log.d("TAG", "Name: $name")
        Log.d("TAG", "Description: $description")
        Log.d("TAG", "Image: $image")
    }
}