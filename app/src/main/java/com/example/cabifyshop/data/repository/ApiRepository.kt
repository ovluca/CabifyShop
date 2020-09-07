package com.example.cabifyshop.data.repository

import com.example.cabifyshop.data.api.RetrofitBuilder.apiService
import com.example.cabifyshop.data.model.Product

class ApiRepository {

    suspend fun getProducts(): List<Product> = apiService.getResponse().products
}