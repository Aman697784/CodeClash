package com.example.codeclash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtPlayerName: EditText
    private lateinit var btnCreateRoom: View
    private lateinit var btnJoinRoom: View
    private lateinit var btnPublicRooms: View
    private lateinit var btnLeaderboard: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GameAuth.login(
            onSuccess = { },
            onError = { error ->
                Toast.makeText(this, "Auth: $error", Toast.LENGTH_SHORT).show()
            }
        )

        edtPlayerName = findViewById(R.id.edtPlayerName)
        btnCreateRoom = findViewById(R.id.btnCreateRoom)
        btnJoinRoom = findViewById(R.id.btnJoinRoom)
        btnPublicRooms = findViewById(R.id.btnPublicRooms)
        btnLeaderboard = findViewById(R.id.btnLeaderboard)

        btnCreateRoom.setOnClickListener {
            val playerName = getPlayerName()
            val intent = Intent(this, CreateRoomActivity::class.java)
            intent.putExtra("PLAYER_NAME", playerName)
            startActivity(intent)
        }

        btnJoinRoom.setOnClickListener {
            val playerName = getPlayerName()
            val intent = Intent(this, JoinRoomActivity::class.java)
            intent.putExtra("PLAYER_NAME", playerName)
            startActivity(intent)
        }

        btnPublicRooms.setOnClickListener {
            val playerName = getPlayerName()
            val intent = Intent(this, PublicRoomsActivity::class.java)
            intent.putExtra("PLAYER_NAME", playerName)
            startActivity(intent)
        }

        btnLeaderboard.setOnClickListener {
            val intent = Intent(this, LeaderboardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getPlayerName(): String {
        val name = edtPlayerName.text.toString().trim()
        return name.ifEmpty { "Player" }
    }
}
