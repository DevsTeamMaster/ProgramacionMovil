package com.devsteammaster.poi

import com.google.gson.annotations.SerializedName

/**data class ListOfPlaces(
    val name: String,
    val description: String,
    val imageURL: String,
    val punctuation: String,
    val temperature: String
)**/

data class ListOfPlaces(
    /**@SerializedName("id")
    val id: Int,**/

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("punctuation")
    val punctuation: String,

    @SerializedName("temperature")
    val temperature: String,

    @SerializedName("location")
    val location: String
)