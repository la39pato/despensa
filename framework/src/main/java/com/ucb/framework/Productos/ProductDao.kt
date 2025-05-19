package com.ucb.framework.Productos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface ProductDao {
    @Query("SELECT * FROM productos")
    suspend fun getAll(): List<ProductDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductDB)

    @Delete
    suspend fun delete(product: ProductDB)

    @Query("SELECT * FROM productos WHERE id = :id")
    suspend fun getById(id: Int): ProductDB?

}