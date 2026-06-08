package com.example.progra3

//import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import java.io.Serializable
import kotlin.jvm.java


class MainActivity : AppCompatActivity() {
    val listaProductos = ArrayList<Producto>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonAgua= findViewById<Button>(R.id.button1)
        val botonGaseosa= findViewById<Button>(R.id.button2)
        val botonGalletas= findViewById<Button>(R.id.button3)
        val botonFacturar = findViewById<Button>(R.id.botonFacturar)
        botonAgua.setOnClickListener {
            val productoExiste= listaProductos.find{ it.nombre =="Producto 1 (Agua)"}
            if (productoExiste != null){
                productoExiste.cantidad++
            }else {
                listaProductos.add(Producto("Producto 1 (Agua)", "Agua", 2.0, 1))
            }
            actualizarTabla(listaProductos)
        }
        botonGaseosa.setOnClickListener {
            val productoExiste= listaProductos.find{ it.nombre =="Producto 2 (Gaseosa)"}
            if (productoExiste != null){
                productoExiste.cantidad++
            }else {
                listaProductos.add(Producto("Producto 2 (Gaseosa)", "Gaseosa", 3.50, 1))
            }
            actualizarTabla(listaProductos)
        }
        botonGalletas.setOnClickListener {
            val productoExiste= listaProductos.find{ it.nombre =="Producto 3 (Galletas)"}
            if (productoExiste != null){
                productoExiste.cantidad++
            }else {
                listaProductos.add(Producto("Producto 3 (Galletas)", "Galletas", 1.50, 1))
            }
            actualizarTabla(listaProductos)
        }
        botonFacturar.setOnClickListener {
            val intent = Intent(this, NotaActivity::class.java)
            intent.putExtra("PRODUCTOS", listaProductos)
            startActivity(intent)
        }
    }
    data class Producto(
        val nombre: String,
        val nombreCorto: String,
        val precio: Double,
        var cantidad: Int
    ) : Serializable

    fun actualizarTabla(lista: ArrayList<Producto>) {
        val tabla = findViewById<TableLayout>(R.id.tablaDetalles)

        // Borrar filas anteriores (menos el header)
        tabla.removeViews(1, tabla.childCount - 1)

        for ((indice, producto) in lista.withIndex()) {
            // Crear TableRow
            val fila = TableRow(this)

            // Crear TextViews
            val txNumero = TextView(this)
            txNumero.text = (indice + 1).toString()

            val txNombre = TextView(this)
            txNombre.text = producto.nombre

            val txPrecio = TextView(this)
            txPrecio.text = "$ ${producto.precio}"

            val txCantidad= TextView(this)
            txCantidad.text="${producto.cantidad}"

            val txSubtotal = TextView(this)
            txSubtotal.text = "$ ${producto.precio * producto.cantidad}"

            // Agregar datos a la tabla
            fila.addView(txNumero)
            fila.addView(txNombre)
            fila.addView(txPrecio)
            fila.addView(txCantidad)
            fila.addView(txSubtotal)
            tabla.addView(fila)
            txPrecio.gravity = android.view.Gravity.CENTER
            txCantidad.gravity = android.view.Gravity.CENTER
            txSubtotal.gravity = android.view.Gravity.CENTER
        }
    }
}
