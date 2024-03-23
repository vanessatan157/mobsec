package com.example.myapplication

import android.net.Uri
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AddPhotoAlternate
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import androidx.core.content.ContextCompat
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext



@Composable
fun ProfileScreen(navController: NavController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    val userData = remember { mutableMapOf<String, String>() }
    val context = LocalContext.current

    LaunchedEffect(currentUser) {
        currentUser?.let {
            fetchUserData(it, firestore, userData)
        }
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
            val imagePickerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let { uploadImageToStorage(it) }
            }

            IconButton(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddPhotoAlternate,
                    contentDescription = "Upload Image"
                )
//            val imagePickerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
//                uri?.let { uploadImageToStorage(it) }
//            }
//
//            // Function to check and request permissions
//            val requestPermissionLauncher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.RequestPermission()
//            ) { isGranted: Boolean ->
//                if (isGranted) {
//                    // Permission is granted, launch image picker
//                    imagePickerLauncher.launch("image/*")
//                } else {
//                    // Permission is denied, handle accordingly
//                    // For example, show a message to the user
//                    // or provide an alternative action
//                }
//            }
//
//            // Check if permission is granted before launching image picker
//            IconButton(
//                onClick = {
//                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                        // Permission is already granted, launch image picker
//                        imagePickerLauncher.launch("image/*")
//                    } else {
//                        // Permission is not granted, request it
//                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//                    }
//                },
//                modifier = Modifier.padding(vertical = 8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.AddPhotoAlternate,
//                    contentDescription = "Upload Image"
//                )
            }

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

// Function to handle image upload
fun uploadImageToStorage(imageUri: Uri) {
    // Get a reference to the Firebase Storage instance
    val storage = Firebase.storage

    // Create a storage reference to the location where you want to store the image
    val imagesRef = storage.reference.child("images/${imageUri.lastPathSegment}")

    // Upload the image to Firebase Storage
    val uploadTask = imagesRef.putFile(imageUri)

    // Listen for the success or failure of the upload task
    uploadTask.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            // Image upload successful
            // You can retrieve the download URL of the uploaded image if needed
            imagesRef.downloadUrl.addOnSuccessListener { downloadUri ->
                // Use the downloadUri to access the uploaded image
                // For example, you can save this URL to Firestore to associate it with the user's profile
                val imageUrl = downloadUri.toString()
                // TODO: Save imageUrl to Firestore or perform any other necessary actions
            }
        } else {
            // Handle upload failure
            val exception = task.exception
            exception?.printStackTrace()
        }
    }
}

suspend fun fetchUserData(currentUser: FirebaseUser, firestore: FirebaseFirestore, userData: MutableMap<String, String>) {
    try {
        val document = firestore.collection("users").document(currentUser.uid).get().await()
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
        // Handle exception appropriately (e.g., log it, show error message to user)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    ProfileScreen(dummyNavController)
}
