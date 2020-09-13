package com.example.cabifyshop.data.repository

import androidx.lifecycle.LiveData
import com.example.cabifyshop.data.local.ProductDao
import com.example.cabifyshop.data.model.Cart
import com.example.cabifyshop.data.model.Product
import com.example.cabifyshop.data.model.ProductAndCart

private const val MAX_QUANTITY: Int = 10

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

	suspend fun updateQuantity(productCode: String, quantity: Int) {
		productDao.updateQuantity(productCode, quantity)
	}

	suspend fun insertProductToCart(product: Product, onError: () -> Unit) {
		// list cart will always have only one item
		val cart = productDao.getItemById(product.code)

		when {
			cart.isEmpty() -> {
				productDao.insertProductInCart(Cart(product.code, quantity = 1))
			}
			cart[0].quantity < MAX_QUANTITY -> {
				productDao.updateQuantity(productCode = product.code)
			}
			cart[0].quantity >= MAX_QUANTITY -> {
				onError()
			}
		}

	}
}