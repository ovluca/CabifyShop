package com.example.cabifyshop.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cabifyshop.data.model.Cart
import com.example.cabifyshop.data.model.ProductAndCart

@Dao
interface CartDao {

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insertProductInCart(cart: Cart)

	@Transaction
	@Query("SELECT * FROM cart")
	fun getCart(): LiveData<List<ProductAndCart>>

}