package com.example.doesitfly.data.remote

import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.*
import java.io.IOException
import java.util.concurrent.CompletableFuture

@OptIn(DelicateCoroutinesApi::class)
class ApiService {

    // API SERVICE CLASS FOR NETWORK METHODS
    fun fetchData(): String {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://data.ffvl.fr/json/sites.json")
            .build()

        val completableFuture = CompletableFuture<String>()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                completableFuture.completeExceptionally(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonResponse = response.body?.string().toString()

                Log.d("TODO 22222", jsonResponse)

                completableFuture.complete(jsonResponse)
            }
        })

        return completableFuture.get()
    }
}