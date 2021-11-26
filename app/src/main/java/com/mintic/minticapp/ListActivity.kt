package com.mintic.minticapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class ListActivity : AppCompatActivity() {

    //Initialize later
    private lateinit var listOfPlaces: ArrayList<ListOfPlaces>
    private lateinit var placesAdapter: PlaceAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(findViewById(R.id.base_toolbar))

        //Initialize RecyclerView
        recyclerView = findViewById(R.id.rvListOfPlaces)
        setUpRecyclerView()
        initDataFromFile()
    }

    private fun setUpRecyclerView() {
        listOfPlaces = arrayListOf()

        //Add a line between each item
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        placesAdapter = PlaceAdapter(listOfPlaces, this) { listOfPlaces ->
            Log.d("ListActivity", "onClick** ${listOfPlaces?.name}")
            placeOnClick(listOfPlaces)
        }
        recyclerView.adapter = placesAdapter
    }

    private fun initDataFromFile() {
        val placesString = readPlacesFromJsonFile()
        try {
            val placesJson = JSONArray(placesString)
            for (i in 0 until placesJson.length()) {
                val placeJson = placesJson.getJSONObject(i)
                val place = ListOfPlaces(
                    placeJson.getString("name"),
                    placeJson.getString("description"),
                    placeJson.getString("imageUrl")
                )
                Log.d("TAG", "generatePlaces: $place")
                listOfPlaces.add(place)
            }
            placesAdapter.notifyDataSetChanged()

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun readPlacesFromJsonFile(): String? {
        var placesString: String? = null
        try {
            val inputStream = assets.open("mock_places.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            placesString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return placesString
    }

    private fun placeOnClick(listOfPlaces: ListOfPlaces?) {
        Log.d("TAG", "Click on: $listOfPlaces")
        listOfPlaces?.let {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(listOfPlaces: ListOfPlaces) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.apply {
            putExtra("listOfPlaces_extra_name", listOfPlaces.name)
            putExtra("listOfPlaces_extra_description", listOfPlaces.description)
            putExtra("listOfPlaces_extra_image", listOfPlaces.imageURL)
        }
        //intent.putExtra("listOfPlaces_extra_name", listOfPlaces.name)

        startActivity(intent)

    }
}