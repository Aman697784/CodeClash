package com.example.codeclash

import com.google.firebase.auth.FirebaseAuth

object GameAuth {

    private val auth = FirebaseAuth.getInstance()

    fun login(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {

        if (auth.currentUser != null) {
            onSuccess(auth.currentUser!!.uid)
            return
        }

        auth.signInAnonymously()
            .addOnSuccessListener {
                val uid = auth.currentUser?.uid

                if (uid != null) {
                    onSuccess(uid)
                } else {
                    onError("User ID not found")
                }
            }
            .addOnFailureListener {
                onError(it.message ?: "Authentication failed")
            }
    }
}
