package com.example.tiendaapp.model

data class Pedido(
    val id: Long? = null,
    val fecha: String? = null,
    val estado: String? = null,
    val cliente: Cliente
)
