package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll two dice and view the results
 * on the screen.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Button in the layout
        val rollButton: Button = findViewById(R.id.button)

        // Set a click listener on the button to roll the dice when the user taps the button
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Roll two dice and update the screen with the results.
     */
    private fun rollDice() {
        // Create two Dice objects with 6 sides and roll them
        val dice1 = Dice(6)
        val dice2 = Dice(6)
        val diceRoll1 = dice1.roll()
        val diceRoll2 = dice2.roll()

        // Update the screen with the dice rolls
        val resultTextView1: TextView = findViewById(R.id.textViewDice1)
        val resultTextView2: TextView = findViewById(R.id.textViewDice2)

        resultTextView1.text = diceRoll1.toString()
        resultTextView2.text = diceRoll2.toString()
    }
}

/**
 * Dice with a fixed number of sides.
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
