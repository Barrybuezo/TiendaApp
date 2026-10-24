package com.example.tiendaapp.model

data class Producto(
    val id: Long? = null,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria: Categoria? = null
)
