package com.ucb.framework.Productos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class ProductDB(
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "cantidad")
    val cantidad: Int,
    @ColumnInfo(name = "unidad")
    val unidad: String,
    @ColumnInfo(name = "fechaVencimiento")
    val fechaVencimiento: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
