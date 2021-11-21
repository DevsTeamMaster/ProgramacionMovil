package com.mintic.minticapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class PlaceAdapter(
    private val listOfPlaces: ArrayList<ListOfPlaces>,
    private val context: Context,
    private val onClick: (ListOfPlaces?) -> Unit
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_list_item, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(placeHolder: PlaceViewHolder, position: Int) {
        val listOfPlacesPosition = listOfPlaces[position]
        placeHolder.bind(place = listOfPlacesPosition)
    }

    override fun getItemCount(): Int = listOfPlaces.size

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageView: ImageView = itemView.findViewById(R.id.imageView_thumb)
        private var nameText: TextView = itemView.findViewById(R.id.textView_place)
        private var resumeText: TextView = itemView.findViewById(R.id.textView_resume)
        private var currentPlace: ListOfPlaces? = null

        fun bind(place: ListOfPlaces) {
            currentPlace = place

            nameText.text = place.name
            resumeText.text = place.description

        }
    }

}