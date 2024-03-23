package com.example.myapplication.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



@Composable
fun UserProfileScreen1(navController: NavController, viewModel: UserProfileViewModel = viewModel()) {
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    // Example of fetching user data from Firebase Firestore
    LaunchedEffect(currentUser?.uid) {
        if (currentUser != null) {
            val db = FirebaseFirestore.getInstance()
            val userDocRef = db.collection("users").document(currentUser.uid)

            // Fetch user data from Firestore
            userDocRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userProfile = documentSnapshot.toObject(UserProfile::class.java)
                        if (userProfile != null) {
                            viewModel.setUserProfileFromFunction(userProfile)
                        }
                    }
                }
                .addOnFailureListener { e ->
                    showToast = true
                    toastMessage = "Error fetching user data: ${e.message}"
                }
        }
    }

    val userProfile = viewModel.userProfile

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
            if (userProfile != null) {
                // Display user profile information
                Text("Welcome, ${userProfile.username}!")
                Text("Email: ${userProfile.email}")

                // User details boxes (centered horizontally)
                UserDetailsBox("Name", userProfile.username)
                UserDetailsBox("Email", userProfile.email)
            } else {
                // Display a loading indicator or handle the case where user data is not yet available
                Text("Loading user data...")
            }

            // Button to log out
            val logoutButtonText = "Log Out"
            val logoutButtonModifier = Modifier.fillMaxWidth()
            Button(
                onClick = {
                    // Firebase sign-out logic
                    auth.signOut()
                    showToast = true
                    toastMessage = "Logged out"
                    // Navigate back to the login screen
                    navController.navigate("login")
                },
                modifier = logoutButtonModifier
            ) {
                Text(logoutButtonText)
            }

            // Button to navigate to user profile screen
            val profileButtonText = "View Profile"
            val profileButtonModifier = Modifier.fillMaxWidth()
            Button(
                onClick = {
                    // Navigate to the user profile screen
                    navController.navigate("profile")
                },
                modifier = profileButtonModifier
            ) {
                Text(profileButtonText)
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
        }
    }
}

@Composable
fun UserDetailsBox(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "$label: $value")
    }
}

data class UserProfile(val username: String, val email: String)

@Composable
@Preview(showBackground = true)
fun UserProfileScreenPreview1() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    UserProfileScreen1(dummyNavController)
}

class UserProfileViewModel : ViewModel() {
    private val _userProfile = mutableStateOf<UserProfile?>(null)

    var userProfile: UserProfile?
        get() = _userProfile.value
        set(value) {
            _userProfile.value = value
        }

    // Rename the function to avoid conflict
    fun setUserProfileFromFunction(profile: UserProfile) {
        userProfile = profile
    }
}

