package com.example.codeclash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class WaitingRoomActivity : AppCompatActivity() {

    private lateinit var txtCode: TextView
    private lateinit var txtPlayers: TextView
    private lateinit var btnStart: Button

    private lateinit var roomRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_room)

        txtCode = findViewById(R.id.txtCode)
        txtPlayers = findViewById(R.id.txtPlayers)
        btnStart = findViewById(R.id.btnStart)

        val code = intent.getStringExtra("ROOM_CODE") ?: ""
        val isHost = intent.getBooleanExtra("IS_HOST", false)

        txtCode.text = "CODE: $code"

        roomRef = FirebaseDatabase.getInstance()
            .getReference("rooms")
            .child(code)

        if (!isHost) {
            btnStart.visibility = Button.GONE
        }

        btnStart.setOnClickListener {

            roomRef.child("status")
                .setValue("RUNNING")
        }

        roomRef.child("players")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val players = StringBuilder()

                    for (player in snapshot.children) {

                        val name = player.child("name")
                            .getValue(String::class.java)

                        players.append("👤 ")
                            .append(name)
                            .append("\n")
                    }

                    txtPlayers.text = players.toString()
                }

                override fun onCancelled(error: DatabaseError) {}
            })

        roomRef.child("status")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val status = snapshot.getValue(String::class.java)

                    if (status == "RUNNING") {

                        val intent = Intent(
                            this@WaitingRoomActivity,
                            GameActivity::class.java
                        )

                        intent.putExtra("ROOM_CODE", code)

                        startActivity(intent)

                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}
