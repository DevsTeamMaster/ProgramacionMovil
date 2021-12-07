package com.devsteammaster.poi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleView = view.findViewById<TextView>(R.id.titleView_bar)
        val textViewResume = view.findViewById<TextView>(R.id.textView_resume)
        val imageViewPlace = view.findViewById<ImageView>(R.id.imageView_detail)


        setFragmentResultListener("places") {
            key, bundle ->
            val title = bundle.getString("nameBundle")
            val description = bundle.getString("descriptionBundle")
            val image = bundle.getString("imageBundle")

            titleView.text = title
            textViewResume.text = description

            Glide.with(view).load(image).into(imageViewPlace)
        }
    }
}