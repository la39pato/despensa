package com.ucb.framework.Productos

import com.ucb.domain.Producto

// De dominio a entidad de base de datos
fun Producto.toEntity(): ProductDB {
    return ProductDB(
        nombre = nombre,
        cantidad = cantidad,
        unidad = unidad,
        fechaVencimiento = fechaVencimiento
    )
}

// De entidad de base de datos a modelo de dominio
fun ProductDB.toModel(): Producto {
    return Producto(
        id = id,
        nombre = nombre,
        cantidad = cantidad,
        unidad = unidad,
        fechaVencimiento = fechaVencimiento
    )
}

// Lista de entidades a lista de modelos
fun List<ProductDB>.toModelList(): List<Producto> {
    return this.map { it.toModel() }
}
