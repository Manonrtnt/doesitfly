package com.example.doesitfly.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doesitfly.data.repository.RemoteRepository
import com.example.doesitfly.domain.FlyingSiteBean

class ListFlyingSiteViewModel : ViewModel() {
    var data: MutableLiveData<List<FlyingSiteBean>?> = MutableLiveData()
    var filteredData = MutableLiveData<List<FlyingSiteBean>>()
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)


    /** load flying site data : FlyingSiteBean from API */

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

    fun sortDataWithSearchResult(
        data: MutableLiveData<List<FlyingSiteBean>?>,
        searchConstraint: String
    ): MutableLiveData<List<FlyingSiteBean>> {

        val originalList = data.value ?: listOf()
        val filteredList = originalList.filter { it.nom.contains(searchConstraint, true) }

        filteredData.value = filteredList

        return filteredData
    }
}