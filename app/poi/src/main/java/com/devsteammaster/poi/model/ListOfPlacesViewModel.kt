package com.devsteammaster.poi.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsteammaster.poi.remote.RetrofitFactory
import kotlinx.coroutines.launch

class ListOfPlacesViewModel : ViewModel() {

    private var apiService = RetrofitFactory.apiService()
    private var listOfPlacesList = MutableLiveData<List<ListOfPlaces>>()

    init {
        requestListOfPlaces()
    }

    fun getListOfPlaces(): LiveData<List<ListOfPlaces>> = listOfPlacesList

    private fun requestListOfPlaces() {
        viewModelScope.launch {
            //listOfPlacesList.value = requestSuspendListOfPlaces()
            listOfPlacesList.value = apiService.requestListOfPlaces()
        }
    }

    /**private suspend fun requestSuspendListOfPlaces(): List<ListOfPlaces> {
        return withContext(Dispatchers.IO) {
            apiService.requestListOfPlaces()
        }
    }**/
}