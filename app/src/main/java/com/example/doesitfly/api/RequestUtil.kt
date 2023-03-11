package com.example.doesitfly.api

import android.util.Log
import com.example.doesitfly.domain.FlyingSiteBean
import com.example.doesitfly.utils.URL_API_FLYING_SITE
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

object RequestUtil {
    private val client = OkHttpClient()
    private val gson = Gson()

    fun loadFlyingSite() : List<FlyingSiteBean> {
        //Contrôle de donnée
        //construire requete
        //Faire la requete
        val json = sendGet(URL_API_FLYING_SITE)
        Log.d("Tag", "data: $json")
        //Parse le résultat
        var flyingSite = gson.fromJson(json, Array<FlyingSiteBean>::class.java)
        //Contrôle
        //Extraction
        Log.d("TEST", flyingSite.asList().toString())
        return flyingSite.asList()
    }

    private fun sendGet(url: String): String? {
        var result: String? = null

        try {
            // Create URL
            val url = URL(url)
            // Build request
            val request = Request.Builder().url(url).build()
            // Execute request
            val response = client.newCall(request).execute()
            result = response.body?.string()
        }
        catch(err:Error) {
            print("Error when executing get request: "+err.localizedMessage)
        }
        return result
    }

}