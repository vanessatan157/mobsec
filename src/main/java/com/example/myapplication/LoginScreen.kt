package com.example.myapplication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Add your login screen UI components here
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            Button(
                onClick = {
                    // Firebase authentication logic
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Sign in success, check admin status from Firestore
                                checkAdminStatus(email, navController) { showToastValue, toastMessageValue ->
                                    showToast = showToastValue
                                    toastMessage = toastMessageValue
                                }

                            } else {
                                // Display a message to the user for authentication failures
                                Log.w("LoginScreen", "Authentication failed.", task.exception)
                                showToast = true
                                toastMessage = "Authentication failed."
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            // Display Toast message when showToast is true
            if (showToast) {
                Toast.makeText(
                    context,
                    toastMessage,
                    Toast.LENGTH_SHORT
                ).show()

                // Reset the values to avoid showing the Toast repeatedly
                showToast = false
                toastMessage = ""
            }

            // Text for redirecting to Signup page
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Sign Up")
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable {
                        // Navigate to the signup screen
                        navController.navigate("signupScreen")
                    }
                    .fillMaxWidth(), // Fill the width of the parent
                // Center the text horizontally and vertically
                textAlign = TextAlign.Center
            )
        }
    }
}

// Check admin status from Firestore
// Check admin status from Firestore
fun checkAdminStatus(
    email: String,
    navController: NavController,
    showToast: (Boolean, String) -> Unit // Function to show toast
) {
    val usersCollection = FirebaseFirestore.getInstance().collection("users")

    usersCollection
        .whereEqualTo("email", email)
        .get()
        .addOnSuccessListener { documents ->
            if (!documents.isEmpty) {
                val isAdmin = documents.documents[0].getBoolean("admin") ?: false

                if (isAdmin) {
                    Log.d("LoginScreen", "Admin login successful")
                    navController.navigate("admin")
                } else {
                    Log.d("LoginScreen", "User login successful")
                    navController.navigate("home")
                }
            } else {
                Log.w("LoginScreen", "No user found with email: $email")
                showToast(true, "Authentication failed.") // Call the showToast function
            }
        }
        .addOnFailureListener { exception ->
            Log.w("LoginScreen", "Error getting documents: ", exception)
            showToast(true, "Authentication failed.") // Call the showToast function
        }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    LoginScreen(dummyNavController)
}