package com.example.codeclash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction

class GameActivity : AppCompatActivity() {

    private lateinit var txtQuestion: TextView
    private lateinit var txtTimer: TextView
    private lateinit var txtQuestionNumber: TextView
    private lateinit var txtLiveScore: TextView

    private lateinit var btnA: Button
    private lateinit var btnB: Button
    private lateinit var btnC: Button
    private lateinit var btnD: Button

    private var currentQuestion = 0
    private var score = 0

    private val questions = arrayOf(
        "Which protocol is used for secure web communication?",
        "What does CPU stand for?",
        "Which language is mainly used for Android development?",
        "Which database are we using?"
    )

    private val optionA = arrayOf(
        "HTTP",
        "Central Processing Unit",
        "Java",
        "MySQL"
    )

    private val optionB = arrayOf(
        "FTP",
        "Central Program Unit",
        "Kotlin",
        "MongoDB"
    )

    private val optionC = arrayOf(
        "HTTPS",
        "Computer Processing Unit",
        "Python",
        "Firebase"
    )

    private val optionD = arrayOf(
        "SMTP",
        "Computer Program Unit",
        "C++",
        "Oracle"
    )

    private val answers = arrayOf(
        "C",
        "A",
        "B",
        "C"
    )

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)

        txtQuestion = findViewById(R.id.txtQuestion)
        txtTimer = findViewById(R.id.txtTimer)
        txtQuestionNumber = findViewById(R.id.txtQuestionNumber)
        txtLiveScore = findViewById(R.id.txtLiveScore)

        btnA = findViewById(R.id.btnA)
        btnB = findViewById(R.id.btnB)
        btnC = findViewById(R.id.btnC)
        btnD = findViewById(R.id.btnD)

        showQuestion()

        btnA.setOnClickListener {
            checkAnswer("A")
        }

        btnB.setOnClickListener {
            checkAnswer("B")
        }

        btnC.setOnClickListener {
            checkAnswer("C")
        }

        btnD.setOnClickListener {
            checkAnswer("D")
        }
    }

    private fun showQuestion() {

        if (currentQuestion >= questions.size) {
            finishGame()
            return
        }

        txtQuestionNumber.text =
            "Question ${currentQuestion + 1}/${questions.size}"

        txtLiveScore.text = "Score: $score"

        txtQuestion.text = questions[currentQuestion]

        btnA.text = "A. ${optionA[currentQuestion]}"
        btnB.text = "B. ${optionB[currentQuestion]}"
        btnC.text = "C. ${optionC[currentQuestion]}"
        btnD.text = "D. ${optionD[currentQuestion]}"

        btnA.isEnabled = true
        btnB.isEnabled = true
        btnC.isEnabled = true
        btnD.isEnabled = true

        startTimer()
    }

    private fun startTimer() {

        if (::timer.isInitialized) {
            timer.cancel()
        }

        timer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                txtTimer.text =
                    "⏱ ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {

                txtTimer.text = "⏱ 0"

                currentQuestion++

                showQuestion()
            }

        }.start()
    }

    private fun checkAnswer(answer: String) {

        if (::timer.isInitialized) {
            timer.cancel()
        }

        btnA.isEnabled = false
        btnB.isEnabled = false
        btnC.isEnabled = false
        btnD.isEnabled = false

        if (currentQuestion < questions.size && answer == answers[currentQuestion]) {
            score += 20
        }

        txtLiveScore.text = "Score: $score"

        currentQuestion++

        showQuestion()
    }

    private fun finishGame() {

        if (::timer.isInitialized) {
            timer.cancel()
        }

        val uid = FirebaseAuth.getInstance().currentUser?.uid

        val code = intent.getStringExtra("ROOM_CODE") ?: ""

        if (uid != null) {
            if (code.isNotEmpty()) {
                FirebaseDatabase.getInstance()
                    .getReference("rooms")
                    .child(code)
                    .child("players")
                    .child(uid)
                    .child("score")
                    .setValue(score)
            }

            val userRef = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(uid)

            userRef.child("xp").runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    val currentXp = mutableData.getValue(Int::class.java) ?: 0
                    mutableData.value = currentXp + score
                    return Transaction.success(mutableData)
                }

                override fun onComplete(
                    error: DatabaseError?,
                    committed: Boolean,
                    currentData: DataSnapshot?
                ) {}
            })
        }

        val intent = Intent(
            this,
            ResultActivity::class.java
        )

        intent.putExtra("ROOM_CODE", code)
        intent.putExtra("MY_SCORE", score)

        startActivity(intent)

        finish()
    }

    override fun onDestroy() {

        if (::timer.isInitialized) {
            timer.cancel()
        }

        super.onDestroy()
    }
}
