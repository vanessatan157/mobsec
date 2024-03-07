package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun UserProfileScreen(navController: NavController) {
    // You can replace these values with actual user data from your authentication system
    val username = "John Doe"
    val email = "john.doe@example.com"
    val gender = "Male"
    val contactNumber = "123-456-7890"
    val address = "123 Main St, City, Country"
    val dateOfBirth = "January 1, 1990"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Content inside Surface
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp), // Add horizontal padding for the user detail boxes
                verticalArrangement = Arrangement.Center
            ) {
                // Display user profile information
                Text("Welcome, $username!", textAlign = TextAlign.Center)
                Text("Email: $email", textAlign = TextAlign.Center)

                // User details boxes (centered horizontally)
                UserDetailsBox("Name", username)
                UserDetailsBox("Gender", gender)
                UserDetailsBox("Contact Number", contactNumber)
                UserDetailsBox("Address", address)
                UserDetailsBox("Date of Birth", dateOfBirth)

                // Button to log out
                val context = LocalContext.current
                val auth = FirebaseAuth.getInstance()

                Button(
                    onClick = {
                        // Firebase sign-out logic
                        auth.signOut()
                        Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
                        // Navigate back to the login screen
                        navController.navigate("login")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Log Out")
                }

                // Text for redirecting to Home page (dummy navigation)
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append("Home")
                        }
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable {
                            // Navigate to the home screen (replace with actual navigation logic)
                            navController.navigate("home")
                        }
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        // BottomAppBar
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        ) {
            // Home tab with icon
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("home")
                    }
            )

            // Records tab without icon
            Text(
                text = "Records",
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("records")
                    }
            )

            // Schedule tab without icon
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Schedule",
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("schedule")
                    }
            )

            // Notifications tab without icon
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification",
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("notification")
                    }
            )

            // Account tab without icon
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("account")
                    }
            )
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

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    UserProfileScreen(dummyNavController)
}