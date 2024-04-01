package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()

        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                ProvideFirebaseInstances(auth = auth, firestore = firestore) {
                    // Set up the NavHost with LoginScreen and SignupScreen
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("signup") {
                            SignupScreen(navController)
                        }
                        composable("profile") {
                            ProfileScreen(navController)
                        }
                        composable("edit_profile") {
                            EditProfileScreen(navController)
                        }
                        composable("home") {
                            HomeScreen(navController)
                        }
                        composable("admin") {
                            AdminScreen(navController)
                        }
                        composable("edit_username") {
                            // Replace EditUsernameScreen with the actual implementation
                            // You can use a lambda to pass initial data if needed
                            EditUsernameScreen(navController, initialUsername = "")
                        }
                        composable("edit_email") {
                            // Replace EditEmailScreen with the actual implementation
                            // You can use a lambda to pass initial data if needed
                            EditEmailScreen(navController, initialEmail = "")
                        }
                        composable("edit_mobile") {
                            // Replace EditMobileScreen with the actual implementation
                            // You can use a lambda to pass initial data if needed
                            EditMobileScreen(navController, initialMobile = "")
                        }
                        composable("edit_gender") {
                            // Replace EditGenderScreen with the actual implementation
                            // You can use a lambda to pass initial data if needed
                            EditGenderScreen(navController, initialGender = "")
                        }
                        composable("edit_age") {
                            // Replace EditAgeScreen with the actual implementation
                            // You can use a lambda to pass initial data if needed
                            EditAgeScreen(navController, initialAge = "")
                        }
                    }
                }
            }
        }
    }
}

// Custom Composable to provide FirebaseAuth and FirebaseFirestore instances
@Composable
fun ProvideFirebaseInstances(
    auth: FirebaseAuth,
    firestore: FirebaseFirestore,
    content: @Composable () -> Unit
) {
    content()
}


