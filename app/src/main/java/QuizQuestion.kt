package com.example.codeclash

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)