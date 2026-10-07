package com.example.codeclash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class QuizActivity : AppCompatActivity() {

    private lateinit var txtQuestionNumber: TextView
    private lateinit var txtTimer: TextView
    private lateinit var txtQuestion: TextView
    private lateinit var txtScore: TextView

    private lateinit var btnOption1: Button
    private lateinit var btnOption2: Button
    private lateinit var btnOption3: Button
    private lateinit var btnOption4: Button

    private lateinit var optionButtons: List<Button>

    private var currentQuestion = 0
    private var score = 0

    private var timer: CountDownTimer? = null

    companion object {
        const val TOTAL_QUESTIONS = 15
        const val QUESTION_TIME = 15000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomCode =
            intent.getStringExtra("roomCode") ?: ""

        setContentView(R.layout.activity_quiz)

        txtQuestionNumber = findViewById(R.id.txtQuestionNumber)
        txtTimer = findViewById(R.id.txtTimer)
        txtQuestion = findViewById(R.id.txtQuestion)
        txtScore = findViewById(R.id.txtScore)

        btnOption1 = findViewById(R.id.btnOption1)
        btnOption2 = findViewById(R.id.btnOption2)
        btnOption3 = findViewById(R.id.btnOption3)
        btnOption4 = findViewById(R.id.btnOption4)

        optionButtons = listOf(
            btnOption1,
            btnOption2,
            btnOption3,
            btnOption4
        )

        showQuestion()

        btnOption1.setOnClickListener {
            checkAnswer(0)
        }

        btnOption2.setOnClickListener {
            checkAnswer(1)
        }

        btnOption3.setOnClickListener {
            checkAnswer(2)
        }

        btnOption4.setOnClickListener {
            checkAnswer(3)
        }
    }

    private fun showQuestion() {

        if (currentQuestion >= QuizData.questions.size) {
            finishQuiz()
            return
        }

        val question = QuizData.questions[currentQuestion]

        txtQuestionNumber.text =
            "QUESTION ${currentQuestion + 1} / $TOTAL_QUESTIONS"

        txtQuestion.text = question.question

        btnOption1.text = question.options[0]
        btnOption2.text = question.options[1]
        btnOption3.text = question.options[2]
        btnOption4.text = question.options[3]

        txtScore.text = "Score: $score"

        enableOptions()

        startTimer()
    }

    private fun updateScoreInFirebase() {

        if (roomCode.isEmpty() || uid == null) {
            return
        }

        database
            .child("rooms")
            .child(roomCode)
            .child("players")
            .child(uid!!)
            .child("score")
            .setValue(score)
    }

    private fun startTimer() {

        timer?.cancel()

        timer = object : CountDownTimer(
            QUESTION_TIME,
            1000
        ) {

            override fun onTick(millisUntilFinished: Long) {

                val seconds =
                    millisUntilFinished / 1000

                txtTimer.text = seconds.toString()
            }

            override fun onFinish() {

                txtTimer.text = "0"

                moveToNextQuestion()
            }

        }.start()
    }

    private fun checkAnswer(selectedAnswer: Int) {

        timer?.cancel()

        disableOptions()

        val correctAnswer =
            QuizData.questions[currentQuestion].correctAnswer

        if (selectedAnswer == correctAnswer) {
            score++
            updateScoreInFirebase()
            Toast.makeText(
                this,
                "Correct! 🎉",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                "Wrong answer ❌",
                Toast.LENGTH_SHORT
            ).show()
        }

        txtScore.text = "Score: $score"

        window.decorView.postDelayed({

            moveToNextQuestion()

        }, 500)
    }

    private fun moveToNextQuestion() {

        currentQuestion++

        if (currentQuestion < TOTAL_QUESTIONS) {

            showQuestion()

        } else {

            finishQuiz()
        }
    }

    private fun disableOptions() {

        optionButtons.forEach {
            it.isEnabled = false
        }
    }

    private fun enableOptions() {

        optionButtons.forEach {
            it.isEnabled = true
        }
    }

    private fun finishQuiz() {

        timer?.cancel()

        val intent =
            Intent(this, ResultActivity::class.java)

        intent.putExtra("score", score)

        startActivity(intent)

        finish()
    }

    private val database =
        FirebaseDatabase.getInstance().reference

    private val uid =
        FirebaseAuth.getInstance().currentUser?.uid

    private var roomCode = ""

    override fun onDestroy() {

        timer?.cancel()

        super.onDestroy()
    }
}