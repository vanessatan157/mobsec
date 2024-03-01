package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ProfileScreen(navController: NavController) {
    var username by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("john.doe@example.com") }

    // You can fetch the user data from your data source and update the variables (username, email) accordingly

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display user profile information
        Text(
            text = "Username: $username",
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Email: $email",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
