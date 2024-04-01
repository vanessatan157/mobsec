package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material.TextField
import androidx.compose.material.Button

@Composable
fun EditUsernameScreen(navController: NavController, initialUsername: String) {
    // State for the new username
    val usernameState = remember { mutableStateOf(initialUsername) }

    // Column with TextField for editing username and Save Button
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            label = { Text("New Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Update username in Firestore only if the new username is valid
                    if (isUsernameValid(usernameState.value)) {
                        updateUserUsername(navController, usernameState.value)
                    } else {
                        // Handle invalid username (e.g., display error message)
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

private fun isUsernameValid(username: String): Boolean {
    // Implement validation logic here (e.g., minimum length, allowed characters)
    return username.isNotBlank() && username.length <= 20
}

private fun updateUserUsername(navController: NavController, newUsername: String) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    currentUser?.let { user ->
        val updatedUserData = mapOf("username" to newUsername)
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
