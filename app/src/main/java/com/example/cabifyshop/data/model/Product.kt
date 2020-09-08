package com.example.cabifyshop.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    @ColumnInfo val code: String,
    @ColumnInfo val name: String,
    @ColumnInfo val price: Double
)