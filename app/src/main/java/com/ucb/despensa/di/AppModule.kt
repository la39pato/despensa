package com.ucb.despensa.di

import com.google.firebase.auth.FirebaseAuth
import com.ucb.data.repository.IAuthRepository
import com.ucb.data.repository.IProductoRepository
import com.ucb.framework.ProductoRepositoryFirestore
import com.ucb.framework.Usuario.AuthRepository
import com.ucb.usecases.Producto.ActualizarProducto
import com.ucb.usecases.Producto.AgregarProducto
import com.ucb.usecases.Producto.EliminarProducto
import com.ucb.usecases.Producto.ObtenerProductos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // --- Firebase Auth ---
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): IAuthRepository {
        return AuthRepository(auth)
    }

    // --- Producto Repository usando Firestore ---
    @Provides
    @Singleton
    fun provideProductoRepository(): IProductoRepository {
        return ProductoRepositoryFirestore()
    }

    // --- Use Cases para Producto ---
    @Provides
    @Singleton
    fun provideAgregarProductoUseCase(repository: IProductoRepository): AgregarProducto {
        return AgregarProducto(repository)
    }

    @Provides
    @Singleton
    fun provideActualizarProductoUseCase(repository: IProductoRepository): ActualizarProducto {
        return ActualizarProducto(repository)
    }

    @Provides
    @Singleton
    fun provideEliminarProductoUseCase(repository: IProductoRepository): EliminarProducto {
        return EliminarProducto(repository)
    }

    @Provides
    @Singleton
    fun provideObtenerProductosUseCase(repository: IProductoRepository): ObtenerProductos {
        return ObtenerProductos(repository)
    }
}
