package com.example.doesitfly.data.repository

import android.util.Log
import com.example.doesitfly.domain.FlyingSiteBean
import com.example.doesitfly.data.remote.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RemoteRepository() {

    companion object {
        // Init API Service class
        private val apiService = ApiService()

        //TODO: BUSINESS LOGIC (BL) TO PERFORM OUR API METHODS
        // Note: BL is used to manages communication between an end user application and a database
        fun getFlyingSitesFromApi(): List<FlyingSiteBean> {
            val jsonData = apiService.fetchData()
            Log.d("JSON", jsonData)
            // Convert JSON Response to List of FlyingSites
            val flyingSites = Gson().fromJson<List<FlyingSiteBean>>(jsonData, object : TypeToken<List<FlyingSiteBean>>() {}.type)
            Log.d("TODO 0000", jsonData.toString())
            return flyingSites ?: emptyList()
        }
    }

}