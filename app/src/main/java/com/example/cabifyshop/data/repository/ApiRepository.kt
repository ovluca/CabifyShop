package com.example.cabifyshop.data.repository

import com.example.cabifyshop.data.api.RetrofitBuilder.apiService

class ApiRepository {

    suspend fun getProducts() = apiService.getProducts()
}