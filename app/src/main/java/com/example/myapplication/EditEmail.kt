package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun EditEmailScreen(navController: NavController, initialEmail: String) {
    // State for the new email
    val emailState = remember { mutableStateOf(initialEmail) }

    // Column with TextField for editing email and Save Button
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("New Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Update email in Firestore
                val currentUser = FirebaseAuth.getInstance().currentUser
                val firestore = FirebaseFirestore.getInstance()
                currentUser?.let { user ->
                    val updatedUserData = mapOf("email" to emailState.value)
                    firestore.collection("users").document(user.uid)
                        .update(updatedUserData)
                        .addOnSuccessListener {
                            // Successfully updated data
                            navController.popBackStack()
                        }
                        .addOnFailureListener { e ->
                            // Handle failure
                            e.printStackTrace()
                        }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}