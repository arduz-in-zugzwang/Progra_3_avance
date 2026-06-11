package com.example.progra3

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.NavHostFragment
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.NavigationUI
//import androidx.navigation.ui.onNavDestinationSelected
//import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.core.view.updatePadding
//dados
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


/**
 * DiceRoller demonstrates simple interactivity in an Android app.
 * It contains one button that updates a text view with a random
 * value between 1 and 6.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var diceImage: ImageView
    private lateinit var diceImage2: ImageView
    private lateinit var diceImage3: ImageView
    private lateinit var diceImage4: ImageView
    private var randomInt: Int? = 0
    private var victoriasJ1 = 0
    private var victoriasJ2 = 0
    private var empates = 0
    private var rondas = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)
        diceImage = findViewById(R.id.dado1_j1)
        diceImage2 = findViewById(R.id.dado2_j1)
        diceImage3 = findViewById(R.id.dado1_j2)
        diceImage4 = findViewById(R.id.dado2_j2)
        // Get the Button view from the layout and assign a click
        // listener to it.
        val rollButton: Button = findViewById(R.id.btn_lanzar)
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Click listener for the Roll button.
     */
    private fun rollDice() {
        val d1j1 = rollSingleDice(diceImage)
        val d2j1 = rollSingleDice(diceImage2)
        val d1j2 = rollSingleDice(diceImage3)
        val d2j2 = rollSingleDice(diceImage4)

        val combJ1 = combinacion(d1j1, d2j1)
        val combJ2 = combinacion(d1j2, d2j2)

        findViewById<TextView>(R.id.combinacion_j1).text = "COMBINACIÓN: $combJ1"
        findViewById<TextView>(R.id.combinacion_j2).text = "COMBINACIÓN: $combJ2"

        // Determinar ganador
        val jerarquia = listOf(21, 66, 55, 44, 33, 22, 11, 65, 64, 63, 62, 61, 54, 53, 52, 51, 43, 42, 41, 32, 31)
        val posJ1 = jerarquia.indexOf(combJ1)
        val posJ2 = jerarquia.indexOf(combJ2)

        rondas++

        if (posJ1 < posJ2) {
            victoriasJ1++
            findViewById<TextView>(R.id.ganador_ronda).text = "GANADOR: JUGADOR 1"
            findViewById<TextView>(R.id.victorias_j1).text = "VIC J1: $victoriasJ1"
        } else if (posJ2 < posJ1) {
            victoriasJ2++
            findViewById<TextView>(R.id.ganador_ronda).text = "GANADOR: JUGADOR 2"
            findViewById<TextView>(R.id.victorias_j2).text = "VIC J2: $victoriasJ2"
        } else {
            empates++
            findViewById<TextView>(R.id.ganador_ronda).text = "EMPATE"
            findViewById<TextView>(R.id.empates).text = "EMPATES: $empates"
        }
    }
    private fun rollSingleDice(diceImageView: ImageView):Int {
        val result = (1..6).random()
        val drawable = when (result) {
            1 -> R.drawable.cara_1
            2 -> R.drawable.cara_2
            3 -> R.drawable.cara_3
            4 -> R.drawable.cara_4
            5 -> R.drawable.cara_5
            else -> R.drawable.cara_6
        }
        diceImageView.setImageResource(drawable)
        diceImageView.animate().rotationBy(360f).setDuration(500).start()
        return result
    }
    fun combinacion(a: Int, b: Int): Int {
        return maxOf(a, b) * 10 + minOf(a, b)
    }
}
