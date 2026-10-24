package com.example.tiendaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tiendaapp.model.*
import com.example.tiendaapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResultado = findViewById(R.id.tvResultado)

        findViewById<Button>(R.id.btnCategorias).setOnClickListener { cargarCategorias() }
        findViewById<Button>(R.id.btnProductos).setOnClickListener { cargarProductos() }
        findViewById<Button>(R.id.btnClientes).setOnClickListener { cargarClientes() }
        findViewById<Button>(R.id.btnPedidos).setOnClickListener { cargarPedidos() }
    }

    private fun cargarCategorias() {
        RetrofitClient.apiService.getCategorias().enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(call: Call<List<Categoria>>, response: Response<List<Categoria>>) {
                if (response.isSuccessful) {
                    val lista = response.body()
                    if (lista.isNullOrEmpty()) {
                        tvResultado.text = "No hay categorías registradas"
                    } else {
                        val sb = StringBuilder()
                        lista.forEach {
                            sb.append("ID: ${it.id}\nNombre: ${it.nombre}\nDescripción: ${it.descripcion ?: "N/A"}\n\n")
                        }
                        tvResultado.text = sb.toString().trim()
                    }
                } else {
                    tvResultado.text = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                mostrarError(t)
            }
        })
    }

    private fun cargarProductos() {
        RetrofitClient.apiService.getProductos().enqueue(object : Callback<List<Producto>> {
            override fun onResponse(call: Call<List<Producto>>, response: Response<List<Producto>>) {
                if (response.isSuccessful) {
                    val lista = response.body()
                    if (lista.isNullOrEmpty()) {
                        tvResultado.text = "No hay productos registrados"
                    } else {
                        val sb = StringBuilder()
                        lista.forEach {
                            sb.append("ID: ${it.id}\nProducto: ${it.nombre}\nPrecio: Q${it.precio}\nStock: ${it.stock}\nCategoría: ${it.categoria?.nombre ?: "Sin categoría"}\n\n")
                        }
                        tvResultado.text = sb.toString().trim()
                    }
                } else {
                    tvResultado.text = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                mostrarError(t)
            }
        })
    }

    private fun cargarClientes() {
        RetrofitClient.apiService.getClientes().enqueue(object : Callback<List<Cliente>> {
            override fun onResponse(call: Call<List<Cliente>>, response: Response<List<Cliente>>) {
                if (response.isSuccessful) {
                    val lista = response.body()
                    if (lista.isNullOrEmpty()) {
                        tvResultado.text = "No hay clientes registrados"
                    } else {
                        val sb = StringBuilder()
                        lista.forEach {
                            sb.append("ID: ${it.id}\nNombre: ${it.nombre}\nEmail: ${it.email}\nTeléfono: ${it.telefono ?: "N/A"}\n\n")
                        }
                        tvResultado.text = sb.toString().trim()
                    }
                } else {
                    tvResultado.text = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Cliente>>, t: Throwable) {
                mostrarError(t)
            }
        })
    }

    private fun cargarPedidos() {
        RetrofitClient.apiService.getPedidos().enqueue(object : Callback<List<Pedido>> {
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                if (response.isSuccessful) {
                    val lista = response.body()
                    if (lista.isNullOrEmpty()) {
                        tvResultado.text = "No hay pedidos registrados"
                    } else {
                        val sb = StringBuilder()
                        lista.forEach {
                            sb.append("Pedido No: ${it.id}\nEstado: ${it.estado}\nCliente: ${it.cliente.nombre}\nFecha: ${it.fecha}\n\n")
                        }
                        tvResultado.text = sb.toString().trim()
                    }
                } else {
                    tvResultado.text = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                mostrarError(t)
            }
        })
    }

    private fun mostrarError(t: Throwable) {
        tvResultado.text = "Error de conexión con la API"
        Toast.makeText(this, "Fallo de red", Toast.LENGTH_SHORT).show()
    }
}