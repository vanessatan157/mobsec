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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.core.util.PatternsCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun SignupScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }

    val genderOptions = listOf("Male", "Female")
    var selectedGender by remember { mutableStateOf(genderOptions[0]) }

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
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
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

            TextField(
                value = selectedGender,
                onValueChange = { selectedGender = it },
                label = { Text("Gender") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
            )

            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
            )

            TextField(
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it },
                label = { Text("Date of Birth") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
            )


            Button(
                onClick = {
                    if (isValidInput(name, username, email, password)) {
                        // For simplicity, let's just navigate to another screen without any signup logic
                        signUp(name, email, password, address, gender, dateOfBirth) { success, message ->
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

            // Text for redirecting to login page
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

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    SignupScreen(dummyNavController)
}

fun isValidInput(name: String, username: String, email: String, password: String): Boolean {
    return !name.isBlank() && !username.isBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
}

fun signUp(
    name: String,
    email: String,
    password: String,
    address: String,
    gender: String,
    dateOfBirth: String,
    onComplete: (Boolean, String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()

    try {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Successfully created user in Authentication
                    val currentUser = FirebaseAuth.getInstance().currentUser
                    val userId = currentUser?.uid

                    userId?.let {
                        val userReference = FirebaseDatabase.getInstance().getReference("users/$it")
                        val userData = User(it, name, email, address, gender, dateOfBirth)

                        userReference.setValue(userData)
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    // Successfully wrote user data to Realtime Database
                                    onComplete(true, "Sign up successful")
                                } else {
                                    // Failed to write user data to Realtime Database
                                    onComplete(false, "Failed to write user data to Realtime Database: ${dbTask.exception?.localizedMessage}")
                                }
                            }
                    } ?: run {
                        onComplete(false, "User ID is null")
                    }
                } else {
                    // Failed to create user in Authentication
                    val exception = task.exception
                    val message = exception?.localizedMessage ?: "Sign up failed"
                    onComplete(false, message)
                }
            }
    } catch (e: Exception) {
        e.printStackTrace()
        onComplete(false, "An error occurred during sign up: ${e.message}")
    }
}


data class User(
    val userId: String,
    val name: String,
    val email: String,
    val address: String,
    val gender: String,
    val dateOfBirth: String
)