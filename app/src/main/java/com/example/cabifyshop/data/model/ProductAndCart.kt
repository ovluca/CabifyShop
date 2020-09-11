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

) {

	fun getTotalPrice(): Double {
		return cart.quantity * product.price
	}

	private fun applyVoucherDiscount(): Double {
		if (cart.quantity > 1) {
			val remained = cart.quantity % 2

			return if (remained == 0) {
				getTotalPrice() * 0.5
			} else {
				(cart.quantity / 2) * product.price + product.price
			}
		}

		return getTotalPrice()
	}

	private fun applyBulkDiscount(): Double {
		if (cart.quantity >= 3) {
			val newPrice = 19.0
			return cart.quantity * newPrice
		}

		return getTotalPrice()
	}

	fun applyDiscount(): Double {
		if (product.code == "VOUCHER") {
			return applyVoucherDiscount()
		} else if (product.code == "TSHIRT") {
			return applyBulkDiscount()
		}
		return getTotalPrice()
	}
}
