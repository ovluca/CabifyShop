package com.example.cabifyshop.data.repository

import androidx.lifecycle.LiveData
import com.example.cabifyshop.data.local.ProductDao
import com.example.cabifyshop.data.model.Product

class LocalRepository(private val productDao: ProductDao) {

	fun getAllProducts(): LiveData<List<Product>> {
		return productDao.getProducts()
	}
}