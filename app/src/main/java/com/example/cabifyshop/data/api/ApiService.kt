package com.example.cabifyshop.data.api

import retrofit2.http.GET
/**
 * this interface it's used for setting up the API requests
 * */
interface ApiService {
    @GET("Products.json")
    suspend fun getResponse(): Response
}