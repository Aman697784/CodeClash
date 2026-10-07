package com.example.codeclash

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class CreateRoomActivity : AppCompatActivity() {

    private lateinit var txtRoomCode: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)

        txtRoomCode = findViewById(R.id.txtRoomCode)

        val playerName = intent.getStringExtra("PLAYER_NAME") ?: "Player"

        val code = generateCode()
        txtRoomCode.text = code

        val uid = FirebaseAuth.getInstance().currentUser?.uid

        if (uid == null) {
            Toast.makeText(this, "Authentication error", Toast.LENGTH_LONG).show()
            return
        }

        val room = FirebaseDatabase.getInstance()
            .getReference("rooms")
            .child(code)

        FirebaseDatabase.getInstance()
            .getReference("users")
            .child(uid)
            .child("name")
            .setValue(playerName)

        val player = hashMapOf(
            "name" to playerName,
            "score" to 0
        )

        val roomData = hashMapOf(
            "hostUid" to uid,
            "status" to "WAITING",
            "mode" to "Public",
            "players" to mapOf(uid to player)
        )

        room.setValue(roomData)
            .addOnSuccessListener {

                val intent = Intent(this, WaitingRoomActivity::class.java)

                intent.putExtra("ROOM_CODE", code)
                intent.putExtra("PLAYER_NAME", playerName)
                intent.putExtra("IS_HOST", true)

                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Room creation failed",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private fun generateCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

        return (1..6)
            .map { chars[Random.nextInt(chars.length)] }
            .joinToString("")
    }
}
