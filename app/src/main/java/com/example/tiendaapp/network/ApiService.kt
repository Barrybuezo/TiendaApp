package com.example.tiendaapp.network

import com.example.tiendaapp.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // CATEGORIAS
    @GET("api/categorias")
    fun getCategorias(): Call<List<Categoria>>

    @POST("api/categorias")
    fun crearCategoria(@Body categoria: Categoria): Call<Categoria>

    // PRODUCTOS
    @GET("api/productos")
    fun getProductos(): Call<List<Producto>>

    @POST("api/productos")
    fun crearProducto(@Body producto: Producto): Call<Producto>

    // CLIENTES
    @GET("api/clientes")
    fun getClientes(): Call<List<Cliente>>

    @POST("api/clientes")
    fun crearCliente(@Body cliente: Cliente): Call<Cliente>

    // PEDIDOS
    @GET("api/pedidos")
    fun getPedidos(): Call<List<Pedido>>

    @POST("api/pedidos")
    fun crearPedido(@Body pedido: Pedido): Call<Pedido>
}