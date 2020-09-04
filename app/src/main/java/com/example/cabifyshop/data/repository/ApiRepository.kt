package com.example.cabifyshop.data.repository

import com.example.cabifyshop.data.api.ApiService

class ApiRepository(private val apiService: ApiService) {

    suspend fun getProducts() = apiService.getProducts()
}