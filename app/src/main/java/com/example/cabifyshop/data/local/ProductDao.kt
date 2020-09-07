package com.example.cabifyshop.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cabifyshop.data.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(products: List<Product>)
}