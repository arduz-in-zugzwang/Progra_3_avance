package com.example.progra3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar

class ResultadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarResultados)
        setSupportActionBar(toolbar)

        // Recibir datos
        val victoriasJ1 = intent.getIntExtra("victorias_j1", 0)
        val victoriasJ2 = intent.getIntExtra("victorias_j2", 0)
        val empates = intent.getIntExtra("empates", 0)
        val rondas = intent.getIntExtra("rondas", 0)
        val nombreJ1 = intent.getStringExtra("nombre_j1") ?: "Jugador 1"
        val nombreJ2 = intent.getStringExtra("nombre_j2") ?: "Jugador 2"
        val combJ1 = intent.getIntExtra("comb_j1", 0)
        val combJ2 = intent.getIntExtra("comb_j2", 0)
        val d1j1 = intent.getIntExtra("d1j1", 1)
        val d2j1 = intent.getIntExtra("d2j1", 1)
        val d1j2 = intent.getIntExtra("d1j2", 1)
        val d2j2 = intent.getIntExtra("d2j2", 1)

        // Marcador
        findViewById<TextView>(R.id.res_victorias_j1).text = "$nombreJ1: $victoriasJ1"
        findViewById<TextView>(R.id.res_victorias_j2).text = "$nombreJ2: $victoriasJ2"
        findViewById<TextView>(R.id.res_empates).text = "Empates: $empates"
        findViewById<TextView>(R.id.res_rondas).text = "Total de rondas: $rondas"

        // Dados última ronda
        findViewById<ImageView>(R.id.res_dado1_j1).setImageResource(dadoDrawable(d1j1))
        findViewById<ImageView>(R.id.res_dado2_j1).setImageResource(dadoDrawable(d2j1))
        findViewById<ImageView>(R.id.res_dado1_j2).setImageResource(dadoDrawable(d1j2))
        findViewById<ImageView>(R.id.res_dado2_j2).setImageResource(dadoDrawable(d2j2))

        // Combinaciones
        findViewById<TextView>(R.id.res_comb_j1).text = "Combinación: $combJ1"
        findViewById<TextView>(R.id.res_comb_j2).text = "Combinación: $combJ2"

        // Ganador general
        val ganador = when {
            victoriasJ1 > victoriasJ2 -> "Ganador: $nombreJ1"
            victoriasJ2 > victoriasJ1 -> "Ganador: $nombreJ2"
            else -> "Empate general"
        }
        findViewById<TextView>(R.id.res_ganador).text = ganador

        // Nuevo juego
        findViewById<Button>(R.id.btn_nuevo_juego).setOnClickListener {
            finish()
        }
    }

    private fun dadoDrawable(valor: Int): Int {
        return when (valor) {
            1 -> R.drawable.cara_1
            2 -> R.drawable.cara_2
            3 -> R.drawable.cara_3
            4 -> R.drawable.cara_4
            5 -> R.drawable.cara_5
            else -> R.drawable.cara_6
        }
    }
}