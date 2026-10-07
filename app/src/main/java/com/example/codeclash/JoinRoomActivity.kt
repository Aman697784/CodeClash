package com.example.codeclash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class JoinRoomActivity : AppCompatActivity() {

    private lateinit var edtRoomCode: EditText
    private lateinit var btnJoin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_room)

        edtRoomCode = findViewById(R.id.edtRoomCode)
        btnJoin = findViewById(R.id.btnJoin)

        val playerName = intent.getStringExtra("PLAYER_NAME") ?: "Player"
        val initialRoomCode = intent.getStringExtra("ROOM_CODE")
        if (!initialRoomCode.isNullOrEmpty()) {
            edtRoomCode.setText(initialRoomCode)
        }

        btnJoin.setOnClickListener {

            val code = edtRoomCode.text.toString()
                .trim()
                .uppercase()

            if (code.length != 6) {
                edtRoomCode.error = "Enter 6 digit room code"
                return@setOnClickListener
            }

            val uid = FirebaseAuth.getInstance().currentUser?.uid

            if (uid == null) {
                Toast.makeText(this, "Authentication error", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val room = FirebaseDatabase.getInstance()
                .getReference("rooms")
                .child(code)

            room.get().addOnSuccessListener { snapshot ->

                if (!snapshot.exists()) {

                    Toast.makeText(
                        this,
                        "Room not found",
                        Toast.LENGTH_LONG
                    ).show()

                    return@addOnSuccessListener
                }

                val status = snapshot.child("status")
                    .getValue(String::class.java)

                if (status != "WAITING") {

                    Toast.makeText(
                        this,
                        "Game already started",
                        Toast.LENGTH_LONG
                    ).show()

                    return@addOnSuccessListener
                }

                val player = hashMapOf(
                    "name" to playerName,
                    "score" to 0
                )

                FirebaseDatabase.getInstance()
                    .getReference("users")
                    .child(uid)
                    .child("name")
                    .setValue(playerName)

                room.child("players")
                    .child(uid)
                    .setValue(player)
                    .addOnSuccessListener {

                        val intent = Intent(
                            this,
                            WaitingRoomActivity::class.java
                        )

                        intent.putExtra("ROOM_CODE", code)
                        intent.putExtra("PLAYER_NAME", playerName)
                        intent.putExtra("IS_HOST", false)

                        startActivity(intent)
                    }
            }
        }
    }
}
