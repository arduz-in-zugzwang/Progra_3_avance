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


/**
 * DiceRoller demonstrates simple interactivity in an Android app.
 * It contains one button that updates a text view with a random
 * value between 1 and 6.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var diceImage: ImageView
    private var randomInt: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImage = findViewById(R.id.dice_image)

        // Get the Button view from the layout and assign a click
        // listener to it.
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Click listener for the Roll button.
     */
    private fun rollDice() {

        this.randomInt = (1..6).random()

        //val diceImage: ImageView = findViewById(R.id.dice_image)
        val drawableResource = when (randomInt) {
            1 -> R.drawable.cara_1
            2 -> R.drawable.cara_2
            3 -> R.drawable.cara_3
            4 -> R.drawable.cara_4
            5 -> R.drawable.cara_5
            else -> R.drawable.cara_6
        }
        this.diceImage.setImageResource(drawableResource)

        diceImage.animate()
            .rotationBy(360f)
            .setDuration(500)
            .start()
    }
}
