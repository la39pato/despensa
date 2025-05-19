package com.ucb.despensa.di

import android.content.Context
import com.ucb.data.local.IProductoLocalDataSource
import com.ucb.data.local.IUsuarioLocalDataSource
import com.ucb.data.repository.AuthRepository
import com.ucb.data.repository.ProductoRepository
import com.ucb.framework.ProductoLocalDataSource
import com.ucb.framework.UsuarioLocalDataSource
import com.ucb.usecases.Producto.ActualizarProducto
import com.ucb.usecases.Producto.AgregarProducto
import com.ucb.usecases.Producto.EliminarProducto
import com.ucb.usecases.Producto.ObtenerProductos
import com.ucb.usecases.Usuario.IniciarSesion
import com.ucb.usecases.Usuario.RegistrarUsuario
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // --- Local Data Sources ---
    @Provides
    @Singleton
    fun provideProductoLocalDataSource(@ApplicationContext context: Context): IProductoLocalDataSource {
        return ProductoLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideUsuarioLocalDataSource(@ApplicationContext context: Context): IUsuarioLocalDataSource {
        return UsuarioLocalDataSource(context)
    }
    // --- Use Cases para Producto ---
    @Provides
    @Singleton
    fun provideAgregarProductoUseCase(repository: ProductoRepository): AgregarProducto {
        return AgregarProducto(repository)
    }

    @Provides
    @Singleton
    fun provideActualizarProductoUseCase(repository: ProductoRepository): ActualizarProducto {
        return ActualizarProducto(repository)
    }

    @Provides
    @Singleton
    fun provideEliminarProductoUseCase(repository: ProductoRepository): EliminarProducto {
        return EliminarProducto(repository)
    }

    @Provides
    @Singleton
    fun provideObtenerProductosUseCase(repository: ProductoRepository): ObtenerProductos {
        return ObtenerProductos(repository)
    }

    // --- Cases para Usuario ---
    @Provides
    @Singleton
    fun provideIniciarSesionUseCase(repository: AuthRepository): IniciarSesion {
        return IniciarSesion(repository)
    }

    @Provides
    @Singleton
    fun provideRegistrarUsuarioUseCase(repository: AuthRepository): RegistrarUsuario {
        return RegistrarUsuario(repository)
    }
}
