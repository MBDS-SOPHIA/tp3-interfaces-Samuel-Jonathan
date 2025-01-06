package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bouton pour lancer les dés
        val rollButton: Button = findViewById(R.id.button)

        // Ajout d'un gestionnaire de clic pour lancer les dés
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Lancer deux dés et mettre à jour l'écran avec les résultats.
     */
    private fun rollDice() {
        // Crée deux objets Dice avec 6 faces
        val dice1 = Dice(6)
        val dice2 = Dice(6)

        // Lance les dés
        val diceRoll1 = dice1.roll()
        val diceRoll2 = dice2.roll()

        // Met à jour les résultats des dés sur l'interface utilisateur
        val resultTextView1: TextView = findViewById(R.id.textViewDice1)
        val resultTextView2: TextView = findViewById(R.id.textViewDice2)

        resultTextView1.text = diceRoll1.toString()
        resultTextView2.text = diceRoll2.toString()

        // Vérifie si les deux dés affichent le même numéro
        if (diceRoll1 == diceRoll2) {
            // Affiche un message de félicitations
            Toast.makeText(this, "Félicitations ! Vous avez gagné !", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * Dé définit avec un nombre fixe de faces.
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
