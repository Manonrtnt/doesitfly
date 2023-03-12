package com.example.doesitfly.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doesitfly.data.repository.RemoteRepository
import com.example.doesitfly.domain.FlyingSiteBean

class ListFlyingSiteViewModel : ViewModel() {

    // Init variable
    var data: MutableLiveData<List<FlyingSiteBean>?> = MutableLiveData()
    var filteredData = MutableLiveData<List<FlyingSiteBean>>()
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

    /**
     * Method to load data from the API
     * Update [data], [errorMessage], and [runInProgress] fields
     */
    fun loadData(){
        // Reset data
        data.postValue(null)
        errorMessage.postValue(null)
        runInProgress.postValue(true)

        try {
            data.postValue(RemoteRepository.getFlyingSitesFromApi())

        } catch (e : Exception) {
            e.printStackTrace()
            errorMessage.postValue("Connection error")
        }

        runInProgress.postValue(false)
    }

    /**
     * Method to sort the data with a search query
     * update [filteredData]
     */
    fun sortDataWithSearchResult(
        data: MutableLiveData<List<FlyingSiteBean>?>,
        searchConstraint: String) {

        // Get the originalList and filteredList based on the searchConstraint
        val originalList = data.value ?: listOf()
        val filteredList = originalList.filter { it.nom.contains(searchConstraint, true) }

        // Update the filteredData with the filteredList
        filteredData.postValue(filteredList)
    }
}