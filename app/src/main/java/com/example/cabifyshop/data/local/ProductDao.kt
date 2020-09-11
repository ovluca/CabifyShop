package com.example.cabifyshop.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cabifyshop.data.model.Cart
import com.example.cabifyshop.data.model.Product
import com.example.cabifyshop.data.model.ProductAndCart


@Dao
interface ProductDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertProductInCart(cart: Cart)

	@Query("UPDATE cart SET quantity = quantity + 1 WHERE product_code == :productCode")
	suspend fun updateQuantity(productCode: String)

	@Transaction
	@Query("SELECT * FROM cart")
	fun getCartData(): LiveData<List<ProductAndCart>>

	@Query("SELECT * from cart WHERE product_code == :productCode")
	suspend fun getItemById(productCode: String): List<Cart>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insertAll(products: List<Product>)

	@Query("SELECT * FROM products")
	fun getProducts(): LiveData<List<Product>>
}