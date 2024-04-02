package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                // Set up the NavHost with LoginScreen and SignupScreen
                NavHost(navController = navController, startDestination = "chat") {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("signup") {
                        SignupScreen(navController)
                    }
                    composable("chat") {
                        ChatScreen(navController)
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


