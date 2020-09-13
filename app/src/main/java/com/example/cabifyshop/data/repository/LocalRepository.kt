package com.example.cabifyshop.data.repository

import androidx.lifecycle.LiveData
import com.example.cabifyshop.data.local.ProductDao
import com.example.cabifyshop.data.model.Cart
import com.example.cabifyshop.data.model.Product
import com.example.cabifyshop.data.model.ProductAndCart

class LocalRepository(private val productDao: ProductDao) {

	fun getAllProducts(): LiveData<List<Product>> {
		return productDao.getProducts()
	}

	fun getCart(): LiveData<List<ProductAndCart>> {
		return productDao.getCart()
	}

	suspend fun deleteDataFromCart() {
		return productDao.deleteDataFromCart()
	}

	suspend fun insertProductToCart(product: Product) {
		val cart = productDao.getItemById(product.code)

		if (cart.isEmpty()) {
			productDao.insertProductInCart(Cart(product.code, quantity = 1))
		} else {
			productDao.updateQuantity(productCode = product.code)
		}
	}
}