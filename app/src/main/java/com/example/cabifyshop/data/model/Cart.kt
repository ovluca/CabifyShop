package com.example.cabifyshop.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
	@PrimaryKey
	@ColumnInfo val product_code: String,
	@ColumnInfo val quantity: Int
)