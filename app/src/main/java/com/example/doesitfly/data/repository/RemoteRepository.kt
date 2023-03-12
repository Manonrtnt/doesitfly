package com.example.doesitfly.data.repository

import com.example.doesitfly.domain.FlyingSiteBean
import com.example.doesitfly.data.remote.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// BUSINESS LOGIC (BL) TO PERFORM API METHODS
// Note: BL is used to manages communication between an end user application and a database
class RemoteRepository() {
    companion object {
        /** Init [ApiService] */
        private val apiService = ApiService()

        /**
         * Fetches flying sites data from API and returns it as a list of FlyingSiteBean objects
         * @return List<[FlyingSiteBean]>
         */
        fun getFlyingSitesFromApi(): List<FlyingSiteBean> {
            val jsonData = apiService.fetchData()
            // Convert JSON Response to List of FlyingSites
            val flyingSites = Gson().fromJson<List<FlyingSiteBean>>(jsonData, object : TypeToken<List<FlyingSiteBean>>() {}.type)
            return flyingSites ?: emptyList()
        }
    }
}