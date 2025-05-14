package com.ucb.framework.Productos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Query("SELECT * FROM productos")
    suspend fun getAll(): List<ProductEntity>

    @Delete
    suspend fun delete(product: ProductEntity)

    @Query("SELECT * FROM productos WHERE id = :id")
    suspend fun getById(id: Int): ProductEntity?

}