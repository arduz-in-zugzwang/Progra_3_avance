package com.example.progra3

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.progra3.MainActivity.Producto

class NotaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fecha = findViewById<TextView>(R.id.fecha)
        val hora = findViewById<TextView>(R.id.hora)
        val num= findViewById<TextView>(R.id.numerofactura)
        num.text= "N°"+"00001"
        fecha.text= "Fecha: 2026/6/7"
        hora.text= "Hora: nose"
        val volver= findViewById<Button>(R.id.botonVolver)
        volver.setOnClickListener {
            finish()
        }
        @Suppress("DEPRECATION")
        val productos = intent.getSerializableExtra("PRODUCTOS") as ArrayList<MainActivity.Producto>
        tablaActualizada(productos)
    }

    fun tablaActualizada(lista: ArrayList<Producto>) {
        val tabla = findViewById<TableLayout>(R.id.tablaDetalles)
        var total = 0.0
        for ((indice, producto) in lista.withIndex()) {
            val fila = TableRow(this)

            // crear tus TextViews aquí...
            val txNumero = TextView(this)
            txNumero.text = (indice + 1).toString()

            val txNombre = TextView(this)
            txNombre.text = producto.nombreCorto

            val txPrecio = TextView(this)
            txPrecio.text = "$ ${producto.precio}"

            val txCantidad= TextView(this)
            txCantidad.text="${producto.cantidad}"

            val txSubtotal = TextView(this)
            txSubtotal.text = "$ ${producto.precio * producto.cantidad}"

            // acumular el total:
            total += producto.precio * producto.cantidad


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

        // mostrar el total al final
        val totalPago = findViewById<TextView>(R.id.totalPagar)
        totalPago.text = "TOTAL: $ $total"
    }
}