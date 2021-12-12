package com.devsteammaster.poi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PlaceAdapter(
    //private val listOfPlaces: ArrayList<ListOfPlaces>,
    //private val context: ListFragment,
    //private val onClick: (ListOfPlaces?) -> Unit
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private var placesList = mutableListOf<ListOfPlaces>()

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    //This method is executed right application start.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.place_list_item, parent, false)


        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(placeHolder: PlaceViewHolder, position: Int) {
        //val listOfPlacesPosition = listOfPlaces[position]
        val listOfPlacesPosition = placesList[position]
        //placeHolder.bind(place = listOfPlacesPosition)
        placeHolder.titleText.text = listOfPlacesPosition.title
        placeHolder.resumeText.text = listOfPlacesPosition.description
        placeHolder.punctuationText.text = listOfPlacesPosition.punctuation
        placeHolder.temperatureText.text = listOfPlacesPosition.temperature

        Log.d("Image url", "Image ${listOfPlacesPosition.imageUrl}")

        if (listOfPlacesPosition.imageUrl.isEmpty()) {
            Picasso.get().load(R.drawable.la_guajira).into(placeHolder.imageView)
        } else {
            Picasso.get()
                .load(listOfPlacesPosition.imageUrl)
                .into(placeHolder.imageView)
        }
    }

    override fun getItemCount(): Int = placesList.size

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView_thumb)
        var titleText: TextView = itemView.findViewById(R.id.textView_place)
        var resumeText: TextView = itemView.findViewById(R.id.textView_resume)
        var punctuationText: TextView = itemView.findViewById(R.id.textView_punctuation_num)
        var temperatureText: TextView = itemView.findViewById(R.id.textView_temperature_num)
        private var currentPlace: ListOfPlaces? = null

        init {
            itemView.setOnClickListener {
                Log.d("TAG", "ItemView OnClick PlaceAdapter")
                /**currentPlace?.let { listOfPlaces ->
                    onClick(listOfPlaces)
                }**/
                clickListener.onItemClick(adapterPosition)

            }
        }

        /**fun bind(place: ListOfPlaces) {
        currentPlace = place


        titleText.text = place.title
        resumeText.text = place.description
        punctuationText.text = place.punctuation
        temperatureText.text = place.temperature

        Glide.with(context)
        .load(place.imageURL)
        .into(imageView)
        }**/
    }

    fun updateListOfPlaces(places: List<ListOfPlaces>?) {
        this.placesList.clear()
        places?.let { this.placesList.addAll(it) }
        notifyDataSetChanged()
    }

}