package com.example.codeclash

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var txtLeaderboard: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_leaderboard)

        txtLeaderboard = findViewById(R.id.txtLeaderboard)

        FirebaseDatabase.getInstance()
            .getReference("users")
            .orderByChild("xp")
            .limitToLast(10)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val list = mutableListOf<String>()

                        for (user in snapshot.children) {

                            val name = user.child("name")
                                    .getValue(String::class.java) ?: "Anonymous"

                            val xp = user.child("xp")
                                    .getValue(Int::class.java) ?: 0

                            list.add("$name : $xp XP")
                        }

                        if (list.isEmpty()) {
                            txtLeaderboard.text = "No players on leaderboard yet."
                        } else {
                            txtLeaderboard.text = list.reversed()
                                .mapIndexed { index, item ->
                                    "${index + 1}. $item"
                                }
                                .joinToString("\n\n")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        txtLeaderboard.text = "Failed to load leaderboard."
                    }
                }
            )
    }
}
