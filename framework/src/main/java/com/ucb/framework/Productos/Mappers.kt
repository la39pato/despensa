package com.ucb.framework.Productos

import com.ucb.domain.Producto

fun ProductEntity.toDomain(): Producto = Producto(id,nombre,cantidad,unidad,fechaVencimiento)

fun Producto.toEntity(): ProductEntity = ProductEntity(id=id,nombre = nombre, cantidad = cantidad, unidad = unidad, fechaVencimiento = fechaVencimiento)