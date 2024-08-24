package com.example.investwhiz

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isFrontVisible = true
    private var isAnimating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flashcard = findViewById<View>(R.id.flashcard)
        val termView = findViewById<View>(R.id.term)
        val definitionView = findViewById<View>(R.id.definition)

        flashcard.setOnClickListener {
            if (isAnimating) return@setOnClickListener // Prevent multiple clicks during animation

            isAnimating = true

            val flipDuration = 300L // Faster duration for the flip animation
            val pivotX = flashcard.width / 2f
            val pivotY = flashcard.height / 2f

            if (isFrontVisible) {
                // Flip to definition
                flashcard.animate()
                    .rotationX(90f)
                    .setDuration(flipDuration / 2)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withEndAction {
                        termView.visibility = View.GONE
                        definitionView.visibility = View.VISIBLE
                        flashcard.rotationX = -90f // Set the rotation to the back
                        flashcard.animate()
                            .rotationX(0f)
                            .setDuration(flipDuration / 2)
                            .setInterpolator(AccelerateDecelerateInterpolator())
                            .withEndAction {
                                isAnimating = false
                            }
                            .start()
                    }
                    .start()
            } else {
                // Flip to term
                flashcard.animate()
                    .rotationX(-90f)
                    .setDuration(flipDuration / 2)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withEndAction {
                        definitionView.visibility = View.GONE
                        termView.visibility = View.VISIBLE
                        flashcard.rotationX = 90f // Set the rotation to the front
                        flashcard.animate()
                            .rotationX(0f)
                            .setDuration(flipDuration / 2)
                            .setInterpolator(AccelerateDecelerateInterpolator())
                            .withEndAction {
                                isAnimating = false
                            }
                            .start()
                    }
                    .start()
            }

            isFrontVisible = !isFrontVisible
        }
    }
}
