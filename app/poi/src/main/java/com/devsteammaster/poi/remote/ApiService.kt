package com.devsteammaster.poi.remote

import com.devsteammaster.poi.model.ListOfPlaces
import retrofit2.http.GET

interface ApiService {

    @GET("listOfPlaces")
    suspend fun requestListOfPlaces(): List<ListOfPlaces>
}