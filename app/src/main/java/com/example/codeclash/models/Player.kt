package com.example.codeclash.models

data class Player(
    val name: String = "",
    val score: Int = 0,
    val answered: Boolean = false,
    val finished: Boolean = false
)
