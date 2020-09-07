package com.example.cabifyshop.data.api

import retrofit2.http.GET

interface ApiService {
    @GET("Products.json")
    suspend fun getResponse(): Response
}