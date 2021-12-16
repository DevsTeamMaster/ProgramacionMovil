package com.devsteammaster.poi.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.devsteammaster.poi.model.ListOfPlaces
import com.devsteammaster.poi.R
import com.devsteammaster.poi.adapter.PlaceAdapter
import com.devsteammaster.poi.model.ListOfPlacesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A fragment representing a list of Items.
 */
class ListFragment : Fragment(), PlaceAdapter.OnItemClickListener {
    private lateinit var listOfPlaces: ArrayList<ListOfPlaces>
    private lateinit var placesAdapter: PlaceAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ListOfPlacesViewModel
    private lateinit var detailFragment: DetailFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.rvListOfPlaces)

        setupRecyclerView(recyclerView)
        //initDataFromFile()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val settingsButton = view.findViewById<FloatingActionButton>(R.id.float_settings_button)

        viewModel = ViewModelProvider(this).get(ListOfPlacesViewModel::class.java)

        viewModel.getListOfPlaces().observe(viewLifecycleOwner, {
            placesAdapter.updateListOfPlaces(it)
        })

        settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_settingsFragment)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        listOfPlaces = arrayListOf()
        /**placesAdapter = PlaceAdapter(listOfPlaces, this) { listOfPlaces ->
            Log.d("ListFragment", "OnCLick** ${listOfPlaces?.title}")
            listOfPlaces?.let {
                placeOnClick(listOfPlaces)
            }
        }**/
        placesAdapter = PlaceAdapter(this as PlaceAdapter.OnItemClickListener)
        recyclerView.adapter = placesAdapter
    }

    /**private fun readPlacesFromJsonFile(): String? {
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
    }**/

    /**private fun initDataFromFile() {
        val placesString = readPlacesFromJsonFile()
        try {
            val placesJson = JSONArray(placesString)
            for (i in 0 until placesJson.length()) {
                val placeJson = placesJson.getJSONObject(i)
                val place = ListOfPlaces(
                    //placeJson.getInt("id"),
                    placeJson.getString("title"),
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
    }**/

    /**private fun placeOnClick(listOfPlaces: ListOfPlaces) {
        Log.d("TAG", "Click on: $listOfPlaces")
        navigateToDetail(listOfPlaces)
    }**/

    private fun navigateToDetail(places: List<ListOfPlaces>, position: Int) {
        Log.d("navigateToDetail", "Details $places")

            val placesPosition = places[position]
            val bundle = Bundle()
            bundle.apply {
                putString("titleBundle", placesPosition.title)
                putString("descriptionBundle", placesPosition.description)
                putString("imageBundle", placesPosition.imageUrl)
                putString("punctuationBundle", placesPosition.punctuation)
                putString("temperatureBundle", placesPosition.temperature)
                putString("locationBundle", placesPosition.location)

                bundle.let { parentFragmentManager.setFragmentResult("places", bundle) }
                Log.d("TAG", "Este es el Bundle: $bundle")
        }
        gotoDetail()
    }

    /**private fun navigateToDetail(listOfPlaces: ListOfPlaces) {
        val bundle = Bundle()
        bundle.apply {
            //putInt("id", listOfPlaces.id)
            putString("titleBundle", listOfPlaces.title)
            putString("descriptionBundle", listOfPlaces.description)
            putString("imageBundle", listOfPlaces.imageURL)
            putString("punctuationBundle", listOfPlaces.punctuation)
            putString("temperatureBundle", listOfPlaces.temperature)
            bundle.let { parentFragmentManager.setFragmentResult("places", bundle) }
            Log.d("TAG", "Este es el Bundle: $bundle")
        }
        gotoDetail()
    }**/

    private fun gotoDetail() {
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        Log.d("TAG", "gotoDetail function...")
    }

    override fun onItemClick(position: Int) {
        Log.d("ListFragment", "onItemClick $position")
        viewModel.getListOfPlaces().observe(viewLifecycleOwner, {
            navigateToDetail(it, position)
        })
    }
}