package com.example.cabifyshop.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cabifyshop.data.local.AppDatabase
import com.example.cabifyshop.data.model.Product
import com.example.cabifyshop.data.repository.LocalRepository

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

	private var localRepository: LocalRepository = LocalRepository(AppDatabase.getAppDataBase(application)!!.productDao());

	fun getProducts(): LiveData<List<Product>> {
		return localRepository.getAllProducts()
	}

	suspend fun insertProduct(product: Product, onError: () -> Unit) {
		localRepository.insertProductToCart(product, onError = onError)
	}
}