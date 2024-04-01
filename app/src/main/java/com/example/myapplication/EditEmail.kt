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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Update email in Firestore only if the new email is valid
                    if (isEmailValid(emailState.value)) {
                        updateEmailInFirestore(navController, emailState.value)
                    } else {
                        // Handle invalid email (e.g., display error message)
                    }
                },
                modifier = Modifier.weight(1f) // Shortens the button
            ) {
                Text("Save")
            }

            Spacer(modifier = Modifier.width(8.dp)) // Add space between buttons

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f) // Shortens the button
            ) {
                Text("Back to Profile")
            }
        }
    }
}

private fun isEmailValid(email: String): Boolean {
    // Implement email validation logic here
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun updateEmailInFirestore(navController: NavController, newEmail: String) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    currentUser?.let { user ->
        val updatedUserData = mapOf("email" to newEmail)
        firestore.collection("users").document(user.uid)
            .update(updatedUserData)
            .addOnSuccessListener {
                // Successfully updated data
                navController.popBackStack()
            }
            .addOnFailureListener { e ->
                // Handle failure
                e.printStackTrace()
                // Display error message to the user
            }
    }
}
