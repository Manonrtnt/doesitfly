package com.example.doesitfly.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doesitfly.data.repository.RemoteRepository
import com.example.doesitfly.domain.FlyingSiteBean

class ListFlyingSiteViewModel : ViewModel() {
    val data: MutableLiveData<List<FlyingSiteBean>?> = MutableLiveData()
    var errorMessage = MutableLiveData("ERROR")

    /** load flying site data : FlyingSiteBean from API */

    fun loadData(){
        // Reset data
        data.postValue(null)
        errorMessage.postValue(null)

        try {
            data.postValue(RemoteRepository.getFlyingSitesFromApi())

        } catch (e : Exception) {
            e.printStackTrace()
            errorMessage.postValue("Connection error")
        }
    }

//        viewModelScope.launch {
//            try {
//                val sites = withContext(Dispatchers.IO) {
//                    // Effectuez votre requête réseau ici
//                    // Cette ligne de code doit être exécutée sur un thread différent du thread principal
//                    loadFlyingSite()
//                }
//
//                // Mettez à jour les données de l'objet MutableLiveData
//                data.value = sites
//                errorMessage.value = null
//            } catch (e: Exception) {
//                // Si une erreur se produit, mettez à jour l'objet MutableLiveData d'erreur
//                errorMessage.value = e.message
//            }
//        }
//    }

}