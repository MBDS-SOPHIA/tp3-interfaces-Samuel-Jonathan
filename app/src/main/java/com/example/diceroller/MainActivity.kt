package com.example.diceroller

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    // Déclaration des widgets
    private lateinit var rollButton: Button
    private lateinit var resultTextView1: TextView
    private lateinit var resultTextView2: TextView
    private lateinit var statusTextView: TextView
    private lateinit var slider: Slider
    private lateinit var sliderValueText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des widgets
        rollButton = findViewById(R.id.button)
        resultTextView1 = findViewById(R.id.textViewDice1)
        resultTextView2 = findViewById(R.id.textViewDice2)
        statusTextView = findViewById(R.id.statusTextView)
        slider = findViewById(R.id.slider)
        sliderValueText = findViewById(R.id.sliderValueText)

        // Désactiver le bouton au démarrage
        rollButton.isEnabled = false

        // Mise à jour de la valeur sélectionnée sur le slider
        slider.addOnChangeListener { _, value, _ ->
            sliderValueText.text = value.toInt().toString()
            rollButton.isEnabled = true // Active le bouton une fois qu'une valeur est définie
        }

        // Ajout d'un gestionnaire de clic au bouton pour lancer les dés
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Lancer les dés et vérifier si la somme correspond au nombre choisi.
     */
    private fun rollDice() {
        // Crée deux dés avec 6 faces
        val dice1 = Dice(6)
        val dice2 = Dice(6)

        // Lance les dés
        val diceRoll1 = dice1.roll()
        val diceRoll2 = dice2.roll()
        val sum = diceRoll1 + diceRoll2

        // Met à jour les résultats sur l'écran
        resultTextView1.text = diceRoll1.toString()
        resultTextView2.text = diceRoll2.toString()

        // Récupère la valeur cible choisie avec le slider
        val targetNumber = slider.value.toInt()

        // Vérifie si la somme est égale à la valeur cible
        if (sum == targetNumber) {
            statusTextView.text = "Félicitations ! Vous avez gagné !"
        } else {
            statusTextView.text = "Désolé, vous avez perdu."
        }
    }
}

/**
 * Dé avec un nombre fixe de faces.
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
