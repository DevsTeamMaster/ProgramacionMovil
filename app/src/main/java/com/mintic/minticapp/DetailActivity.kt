package com.mintic.minticapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("listOfPlaces_extra_name")
        val description = intent.getStringExtra("listOfPlaces_extra_description")
        val imageURL = intent.getStringExtra("listOfPlaces_extra_image")
        Log.d("TAG", "Name: $name")
        Log.d("TAG", "Description: $description")
        Log.d("TAG", "Image: $imageURL")

        val textViewResume = findViewById<TextView>(R.id.textView_resume)
        val title = findViewById<TextView>(R.id.toolbar_title)
        val image = findViewById<ImageView>(R.id.imageView_detail)
        textViewResume.text = description
        title.text = name
    }
}