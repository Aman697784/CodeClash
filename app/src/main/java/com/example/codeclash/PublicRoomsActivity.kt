package com.example.codeclash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class PublicRoomsActivity : AppCompatActivity() {

    private lateinit var roomList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_public_rooms)

        roomList = findViewById(R.id.roomList)
        val playerName = intent.getStringExtra("PLAYER_NAME") ?: "Player"

        FirebaseDatabase.getInstance()
            .getReference("rooms")
            .addValueEventListener(
                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        roomList.removeAllViews()

                        for (room in snapshot.children) {

                            val code = room.key ?: continue

                            val status = room.child("status")
                                    .getValue(String::class.java)

                            val mode = room.child("mode")
                                    .getValue(String::class.java)

                            if (status == "WAITING" && mode == "Public") {

                                val button = Button(this@PublicRoomsActivity)

                                button.text = "🎮 Room $code"

                                button.setOnClickListener {

                                    val intent = Intent(
                                        this@PublicRoomsActivity,
                                        JoinRoomActivity::class.java
                                    )

                                    intent.putExtra("ROOM_CODE", code)
                                    intent.putExtra("PLAYER_NAME", playerName)

                                    startActivity(intent)
                                }

                                roomList.addView(button)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                }
            )
    }
}
