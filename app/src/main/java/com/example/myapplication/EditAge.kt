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
import androidx.compose.runtime.MutableState

@Composable
fun EditAgeScreen(navController: NavController, initialAge: String, errorMessage: MutableState<String?>) {
    // State for the new age
    val ageState = remember { mutableStateOf(initialAge) }

    // Column with TextField for editing age and Save Button
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = ageState.value,
            onValueChange = { ageState.value = it },
            label = { Text("New Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Update age in Firestore only if the new age is valid
                    if (isAgeValid(ageState.value)) {
                        updateAgeInFirestore(navController, ageState.value)
                    } else {
                        // Display an error message if the age is invalid
                        errorMessage.value = "Invalid age. Please enter a valid age."
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

private fun isAgeValid(age: String): Boolean {
    // Implement validation logic for age (e.g., numeric, within a certain range)
    return age.toIntOrNull() in 1..150 // Assuming age is between 1 and 150
}

private fun updateAgeInFirestore(navController: NavController, newAge: String) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    currentUser?.let { user ->
        val updatedUserData = mapOf("age" to newAge)
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
