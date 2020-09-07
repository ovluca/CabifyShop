package com.example.cabifyshop.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.cabifyshop.data.local.AppDatabase
import com.example.cabifyshop.data.model.Product
import com.example.cabifyshop.data.repository.ApiRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ApiRepository()

    private val db = AppDatabase.getAppDataBase(application)

    val products = liveData {
        val products: List<Product> = repository.getProducts()
        populateDatabase(products)
        emit(products)
    }

    private fun populateDatabase(products: List<Product>) {
        GlobalScope.launch { db!!.productDao().insertAll(products) }
    }
}