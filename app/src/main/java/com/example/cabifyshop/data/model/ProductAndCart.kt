package com.example.cabifyshop.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProductAndCart(
	@Embedded val cart: Cart,
	@Relation(
		parentColumn = "product_code",
		entityColumn = "code"
	)
	val product: Product
)