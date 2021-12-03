package com.devsteammaster.poi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlaceAdapter(
    private val listOfPlaces: ArrayList<ListOfPlaces>,
    private val context: ListFragment,
    private val onClick: (ListOfPlaces?) -> Unit
    ) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    //This method is executed right application start.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceAdapter.PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_list_item, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(placeHolder: PlaceAdapter.PlaceViewHolder, position: Int) {
        val listOfPlacesPosition = listOfPlaces[position]
        placeHolder.bind(place = listOfPlacesPosition)
    }

    override fun getItemCount(): Int = listOfPlaces.size

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageView: ImageView = itemView.findViewById(R.id.imageView_thumb)
        private var nameText: TextView = itemView.findViewById(R.id.textView_place)
        private var resumeText: TextView = itemView.findViewById(R.id.textView_resume)
        private var punctuationText: TextView = itemView.findViewById(R.id.textView_punctuation_num)
        private var temperatureText: TextView = itemView.findViewById(R.id.textView_temperature_num)
        private var currentPlace: ListOfPlaces? = null

        init {
            itemView.setOnClickListener {
                Log.d("TAG", "ItemView OnClick")
                currentPlace?.let { listOfPlaces ->
                    onClick(listOfPlaces)
                }
            }
        }

        fun bind(place: ListOfPlaces) {
            currentPlace = place

            nameText.text = place.name
            resumeText.text = place.description
            punctuationText.text = place.punctuation
            temperatureText.text = place.temperature

            Glide.with(context)
                .load(place.imageURL)
                .into(imageView)
        }
    }

}