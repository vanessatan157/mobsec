package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AdminScreen(navController: NavController) {
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
            // Add your home screen UI components here
            Text("Welcome to Admin Screen!")

            // Logout button in the top right corner
            IconButton(
                onClick = {
                    // Navigate to the login screen
                    navController.navigate("login")
                },
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Logout")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminScreenPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    AdminScreen(dummyNavController)
}
