package com.example.cabifyshop.data.api

import com.example.cabifyshop.data.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("Products.json")
    suspend fun getProducts(): List<Product>
}