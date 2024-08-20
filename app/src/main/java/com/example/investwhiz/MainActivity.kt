package com.example.investwhiz

import android.os.Bundle
import android.view.View
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flashcard = findViewById<View>(R.id.flashcard)
        val termView = findViewById<View>(R.id.term)
        val definitionView = findViewById<View>(R.id.definition)

        flashcard.setOnClickListener {
            // Define flip-out animation
            val flipOutAnimation = ScaleAnimation(
                1f, 0f,  // fromX, toX
                1f, 1f,  // fromY, toY
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,  // pivotX
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f   // pivotY
            ).apply {
                duration = 500
                fillAfter = true
            }

            // Define flip-in animation
            val flipInAnimation = ScaleAnimation(
                0f, 1f,  // fromX, toX
                1f, 1f,  // fromY, toY
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,  // pivotX
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f   // pivotY
            ).apply {
                duration = 500
                fillAfter = true
            }

            if (termView.visibility == View.VISIBLE) {
                termView.startAnimation(flipOutAnimation)
                termView.visibility = View.GONE
                definitionView.visibility = View.VISIBLE
                definitionView.startAnimation(flipInAnimation)
            } else {
                definitionView.startAnimation(flipOutAnimation)
                definitionView.visibility = View.GONE
                termView.visibility = View.VISIBLE
                termView.startAnimation(flipInAnimation)
            }
        }
    }
}
