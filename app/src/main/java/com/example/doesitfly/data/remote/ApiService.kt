package com.example.doesitfly.data.remote

import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.*
import java.io.IOException
import java.util.concurrent.CompletableFuture

/** API service class for network methods */
@OptIn(DelicateCoroutinesApi::class)
class ApiService {

    /**
     * This method fetches data from an API endpoint and returns it as a string
     * @return a string representation of the data fetched from the API endpoint
     */
    fun fetchData(): String {
        // create an OkHttpClient instance to make HTTP requests
        val client = OkHttpClient()

        // create a request to the API endpoint
        val request = Request.Builder()
            .url("https://data.ffvl.fr/json/sites.json")
            .build()

        // create a CompletableFuture that will hold the response from the API
        val completableFuture = CompletableFuture<String>()

        // make an asynchronous HTTP request using OkHttp
        client.newCall(request).enqueue(object : Callback {
            // handle any errors that occur during the request
            override fun onFailure(call: Call, e: IOException) {
                completableFuture.completeExceptionally(e)
            }

            // handle the response from the API
            override fun onResponse(call: Call, response: Response) {
                // read the response body as a string
                val jsonResponse = response.body?.string().toString()

                // complete the CompletableFuture with the response body
                completableFuture.complete(jsonResponse)
            }
        })

        // wait for the CompletableFuture to complete and return its result
        return completableFuture.get()
    }
}