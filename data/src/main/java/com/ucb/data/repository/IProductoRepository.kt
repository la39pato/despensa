    package com.ucb.data.repository

    import com.ucb.domain.Producto

    interface IProductoRepository {
        suspend fun agregarProducto(producto: Producto): Boolean
         suspend fun obtenerProductos(): List<Producto>
        suspend fun actualizarProducto(producto: Producto): Boolean
        suspend fun eliminarProducto(id: String): Boolean
    }
