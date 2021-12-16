package com.devsteammaster.poi.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.devsteammaster.poi.model.ListOfPlaces
import com.devsteammaster.poi.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private var placeDetail = mutableListOf<ListOfPlaces>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleView = view.findViewById<TextView>(R.id.titleView_bar)
        val textViewResume = view.findViewById<TextView>(R.id.textView_resume)
        val imageViewPlace = view.findViewById<ImageView>(R.id.imageView_detail)

        Log.d("placeDetail on DetailFragment", "Detail on fragment $placeDetail")

        /**for (i in 0 until placeDetail.size) {
        val placeDetailPositional = placeDetail[i]
        titleView.text = placeDetailPositional.title
        textViewResume.text = placeDetailPositional.description
        }**/

        setFragmentResultListener("places") { key, bundle ->
            val title = bundle.getString("titleBundle")
            val description = bundle.getString("descriptionBundle")
            val image = bundle.getString("imageBundle")
            val locationPlace = bundle.getString("locationBundle")

            titleView.text = title
            textViewResume.text = description
            getMap(locationPlace)

            if (image!!.isEmpty()) {
                Picasso.get().load(R.drawable.la_guajira).into(imageViewPlace)
            } else {
                Glide.with(view).load(image).into(imageViewPlace)
            }
        }
    }

    private fun getMap(location: String?) {

        val geoString = "geo:"
        val packageString = "com.google.android.apps.maps"
        val buttonMap = view?.findViewById<ExtendedFloatingActionButton>(R.id.getMap_button)

        buttonMap?.setOnClickListener {

            val uri = Uri.parse("$geoString + $location")
            val locationIntent = Intent(Intent.ACTION_VIEW, uri)

            locationIntent.setPackage(packageString)
            startActivity(locationIntent)
        }
    }
}