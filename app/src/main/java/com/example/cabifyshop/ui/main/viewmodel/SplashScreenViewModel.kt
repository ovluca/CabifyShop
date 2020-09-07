package com.example.cabifyshop.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cabifyshop.data.model.Product
import com.example.cabifyshop.data.repository.ApiRepository

class SplashScreenViewModel : ViewModel() {
    private val repository = ApiRepository()

    val products = liveData {
        val products: List<Product> = repository.getProducts().products
        emit(products)
    }
}