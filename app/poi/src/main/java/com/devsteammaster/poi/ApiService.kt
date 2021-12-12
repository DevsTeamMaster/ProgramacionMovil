package com.devsteammaster.poi

import retrofit2.http.GET

interface ApiService {

    @GET("listOfPlaces")
    suspend fun requestListOfPlaces(): List<ListOfPlaces>
}