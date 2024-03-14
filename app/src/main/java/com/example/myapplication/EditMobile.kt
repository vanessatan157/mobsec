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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material.TextField
import androidx.compose.material.Button


@Composable
fun EditMobileScreen(navController: NavController, initialMobile: String) {
    // State for the new mobile number
    val mobileState = remember { mutableStateOf(initialMobile) }

    // Column with TextField for editing mobile number and Save Button
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = mobileState.value,
            onValueChange = { mobileState.value = it },
            label = { Text("New Mobile Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Update mobile number in Firestore
                val currentUser = FirebaseAuth.getInstance().currentUser
                val firestore = FirebaseFirestore.getInstance()
                currentUser?.let { user ->
                    val updatedUserData = mapOf("mobileNumber" to mobileState.value)
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