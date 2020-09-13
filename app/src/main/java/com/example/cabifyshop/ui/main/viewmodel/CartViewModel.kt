package com.example.cabifyshop.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cabifyshop.data.local.AppDatabase
import com.example.cabifyshop.data.model.ProductAndCart
import com.example.cabifyshop.data.repository.LocalRepository
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

	private var localRepository: LocalRepository = LocalRepository(AppDatabase.getAppDataBase(application)!!.productDao())

	fun getTotal(products: List<ProductAndCart>): Double {
		var total = 0.0
		products.forEach { product ->
			run {
				total += product.applyDiscount()
			}
		}

		return String.format("%.2f", total).toDouble()
	}

	fun getCart(): LiveData<List<ProductAndCart>> {
		return localRepository.getCart()
	}

	fun deleteDataFromCart() {
		viewModelScope.launch { localRepository.deleteDataFromCart() }
	}

}