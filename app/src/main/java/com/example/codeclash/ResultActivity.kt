package com.example.codeclash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var txtResultMessage: TextView
    private lateinit var txtFinalScore: TextView
    private lateinit var btnPlayAgain: Button
    private lateinit var btnHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        txtResultMessage =
            findViewById(R.id.txtResultMessage)

        txtFinalScore =
            findViewById(R.id.txtFinalScore)

        btnPlayAgain =
            findViewById(R.id.btnPlayAgain)

        btnHome =
            findViewById(R.id.btnHome)

        val score =
            intent.getIntExtra("score", 0)

        txtFinalScore.text =
            "$score / 15"

        txtResultMessage.text =
            when {
                score >= 13 -> "Excellent! 🔥"
                score >= 10 -> "Great Job! 🎉"
                score >= 7 -> "Good Game! 👍"
                else -> "Keep Practicing! 💪"
            }

        btnPlayAgain.setOnClickListener {

            val intent =
                Intent(this, QuizActivity::class.java)

            startActivity(intent)

            finish()
        }

        btnHome.setOnClickListener {

            val intent =
                Intent(this, MainActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)

            finish()
        }
    }
}