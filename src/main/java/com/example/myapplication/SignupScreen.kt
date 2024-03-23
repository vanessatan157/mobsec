package com.example.myapplication

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.core.util.PatternsCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
@Composable
fun SignupScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }

    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // Handle the result of the authentication activity
        }

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Add your signup screen UI components here
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) }
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) }
            )
            // Add new fields for age, gender, and mobile number
            TextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Age") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) } // Date Range icon for age
            )

            TextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Gender") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) } // Person icon for gender
            )

            TextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = { Text("Mobile Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) } // Phone icon for mobile number
            )

            Button(
                onClick = {
                    if (isValidInput(username, email, password, age, gender, mobileNumber)) {
                        // For simplicity, let's just navigate to another screen without any signup logic
                        signUp(username, email, password, age, gender, mobileNumber) { success, message ->
                            if (success) {
                                navController.navigate("login")
                            } else {
                                showToast = true
                                toastMessage = message
                            }
                        }
                    } else {
                        showToast = true
                        toastMessage = "Invalid input. Please check your details."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
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

            // Text for redirecting to the login page
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Already have an account?")
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable {
                        // Navigate to the login screen
                        navController.navigate("login")
                    }
            )
        }
    }
}

@Composable
fun SignupScreenPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    SignupScreen(dummyNavController)
}

fun isValidInput(username: String, email: String, password: String, age: String, gender: String, mobileNumber: String): Boolean {
    // Validate all input fields
    return !username.isBlank() &&
            PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() &&
            password.length >= 6 &&
            age.isNotBlank() && age.toIntOrNull() in 0..150 &&
            (gender.equals("Male", ignoreCase = true) || gender.equals("Female", ignoreCase = true)) &&
            mobileNumber.isNotBlank() && mobileNumber.length == 8 && mobileNumber.isDigitsOnly()
}

fun signUp(username: String, email: String, password: String, age: String, gender: String, mobileNumber: String, onComplete: (Boolean, String) -> Unit) {
    val auth = FirebaseAuth.getInstance()

    try {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    userId?.let {
                        val userMap = mapOf(
                            "username" to username,
                            "email" to email,
                            "admin" to false, // Set admin status initially to false
                            "age" to age,
                            "gender" to gender,
                            "mobileNumber" to mobileNumber
                        )

                        // Use the Firestore instance directly
                        FirebaseFirestore.getInstance().collection("users").document(userId).set(userMap)
                            .addOnSuccessListener {
                                onComplete(true, "Sign up successful")
                            }
                            .addOnFailureListener { e ->
                                onComplete(false, "Error writing user data to Firestore: ${e.message}")
                            }
                    } ?: run {
                        onComplete(false, "User ID is null")
                    }
                } else {
                    val exception = task.exception
                    val message = exception?.localizedMessage ?: "Sign up failed"
                    onComplete(false, message)
                }
            }
    } catch (e: Exception) {
        e.printStackTrace()
        onComplete(false, "An error occurred during sign up")
    }
}
