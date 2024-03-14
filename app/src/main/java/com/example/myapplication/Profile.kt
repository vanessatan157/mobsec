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

@Composable
fun ProfileScreen(navController: NavController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    val userData = remember { mutableMapOf<String, String>() }

    LaunchedEffect(currentUser) {
        fetchUserData(currentUser, firestore, userData)
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
            verticalArrangement = Arrangement.Center
        ) {
            // Add your profile screen UI components here
            Text("Profile")

            // Display user data in preferred order and with custom labels
            ProfileDetail(label = "Username", value = userData["username"] ?: "") {
                navController.navigate("edit_username")
            }
            ProfileDetail(label = "Email", value = userData["email"] ?: "") {
                navController.navigate("edit_email")
            }
            ProfileDetail(label = "Mobile", value = userData["mobileNumber"] ?: "") {
                navController.navigate("edit_mobile")
            }
            ProfileDetail(label = "Gender", value = userData["gender"] ?: "") {
                navController.navigate("edit_gender")
            }
            ProfileDetail(label = "Age", value = userData["age"] ?: "") {
                navController.navigate("edit_age")
            }
        }
    }
}

@Composable
fun ProfileDetail(label: String, value: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = label)
        Row(modifier = Modifier.clickable(onClick = onClick)) {
            Text(text = value, color = Color.Gray)
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Edit",
                tint = Color.Gray
            )
        }
    }
}

suspend fun fetchUserData(currentUser: FirebaseUser?, firestore: FirebaseFirestore, userData: MutableMap<String, String>) {
    currentUser?.let { user ->
        try {
            val document = firestore.collection("users").document(user.uid).get().await()
            if (document.exists()) {
                val data = document.data
                data?.forEach { (key, value) ->
                    if (key != "admin") { // Exclude the "admin" field
                        userData[key ?: ""] = value?.toString() ?: ""
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    ProfileScreen(dummyNavController)
}
