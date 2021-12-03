package com.devsteammaster.poi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

/**
 * A fragment representing a list of Items.
 */
class ListFragment : Fragment() {
    private lateinit var listOfPlaces: ArrayList<ListOfPlaces>
    private lateinit var placesAdapter: PlaceAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.rvListOfPlaces)

        setupRecyclerView()
        initDataFromFile()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun setupRecyclerView() {
        listOfPlaces = arrayListOf()
        placesAdapter = PlaceAdapter(listOfPlaces, this) { listOfPlaces ->
            Log.d("ListFragment", "OnCLick** ${listOfPlaces?.name}")
            listOfPlaces?.let {
                placeOnClick(listOfPlaces)
            }
        }
        recyclerView.adapter = placesAdapter
    }

    private fun readPlacesFromJsonFile(): String? {
        var placesString: String? = null
        try {
            val inputStream = context?.assets?.open("mock_places.json")
            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }
            inputStream?.read(buffer)
            inputStream?.close()

            placesString = buffer?.let { String(it) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return placesString
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
                    placeJson.getString("imageUrl"),
                    placeJson.getString("punctuation"),
                    placeJson.getString("temperature")
                )
                Log.d("TAG", "generatePlaces: $place")
                listOfPlaces.add(place)
            }
            placesAdapter.notifyDataSetChanged()

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun placeOnClick(listOfPlaces: ListOfPlaces) {
        Log.d("TAG", "Click on: $listOfPlaces")
        navigateToDetail(listOfPlaces)
    }


    private fun navigateToDetail(listOfPlaces: ListOfPlaces) {
        val bundle = Bundle()
        bundle.apply {
            putString("nameBundle", listOfPlaces.name)
            putString("descriptionBundle", listOfPlaces.description)
            putString("imageBundle", listOfPlaces.imageURL)
            putString("punctuationBundle", listOfPlaces.punctuation)
            putString("temperatureBundle", listOfPlaces.temperature)
            bundle.let { parentFragmentManager.setFragmentResult("places", bundle) }
            Log.d("TAG", "Este es el Bundle: $bundle")
        }
        gotoDetail()
    }

    private fun gotoDetail() {
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        Log.d("TAG", "gotoDetail function...")
    }
}