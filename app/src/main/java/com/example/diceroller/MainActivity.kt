package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.github.jinatonic.confetti.CommonConfetti

class MainActivity : AppCompatActivity() {

    // Déclaration des widgets
    private lateinit var resultTextView1: TextView
    private lateinit var resultTextView2: TextView
    private lateinit var statusTextView: TextView
    private lateinit var slider: Slider
    private lateinit var sliderValueText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des widgets
        resultTextView1 = findViewById(R.id.textViewDice1)
        resultTextView2 = findViewById(R.id.textViewDice2)
        statusTextView = findViewById(R.id.statusTextView)
        slider = findViewById(R.id.slider)
        sliderValueText = findViewById(R.id.sliderValueText)

        // Ajouter un listener pour détecter les changements sur le slider
        slider.addOnChangeListener { _, value, _ ->
            // Met à jour la valeur sélectionnée
            sliderValueText.text = "Nombre sélectionné : ${value.toInt()}"

            // Lance automatiquement les dés
            rollDice(value.toInt())
        }
    }

    /**
     * Lancer les dés et vérifier si la somme correspond au nombre choisi.
     */
    private fun rollDice(targetNumber: Int) {
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

        // Vérifie si la somme est égale à la valeur cible
        if (sum == targetNumber) {
            statusTextView.text = "Félicitations ! Vous avez gagné !"

            // Animer les dés
            animateDice(resultTextView1)
            animateDice(resultTextView2)

            // Affiche des paillettes
            showConfetti()
        } else {
            statusTextView.text = "Désolé, vous avez perdu."
        }
    }

    /**
     * Animer les dés (bouger de haut en bas).
     */
    private fun animateDice(view: View) {
        val animation = TranslateAnimation(0f, 0f, -50f, 50f)
        animation.duration = 500
        animation.repeatCount = 3
        animation.repeatMode = Animation.REVERSE
        view.startAnimation(animation)
    }

    /**
     * Afficher des paillettes.
     */
    private fun showConfetti() {
        CommonConfetti.rainingConfetti(
            findViewById(R.id.mainLayout),  // ID du parent layout
            intArrayOf(
                android.graphics.Color.RED,
                android.graphics.Color.GREEN,
                android.graphics.Color.BLUE,
                android.graphics.Color.YELLOW
            )
        ).stream(3000) // Durée des confettis en millisecondes
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
