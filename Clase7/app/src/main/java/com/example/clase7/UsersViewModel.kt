package com.example.clase7

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
data class User(val name: String = "", val career: String = "", val studentId: String = "")

class UsersViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private var listener: ListenerRegistration? = null

    var users = mutableStateListOf<User>()
        private set

    init {
        loadUsers()
    }

    fun addUser(user: User) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
                println("Error al guardar usuario: $e")
            }
    }

    private fun loadUsers() {
        listener = db.collection("users")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    println("Error al obtener usuarios: $e")
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    users.clear()
                    for (doc in snapshot.documents) {
                        val user = doc.toObject(User::class.java)
                        if (user != null) {
                            users.add(user)
                        }
                    }
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }
}