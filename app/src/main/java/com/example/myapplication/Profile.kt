package com.example.myapplication

import android.net.Uri
import android.Manifest
import android.content.Intent
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
import android.content.Context
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.layout.ContentScale
import com.google.firebase.storage.StorageReference
import coil.compose.rememberImagePainter
import androidx.compose.foundation.Image


@Composable
fun ProfileScreen(navController: NavController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    val userData = remember { mutableMapOf<String, String>() }
    val context = LocalContext.current

    // State to hold the profile picture URL
    val profilePictureUrl = remember { mutableStateOf<String?>(null) }

    // Initialize the image picker launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { imageUri ->
            currentUser?.let { user ->
                // Only call the function if currentUser is not null
                uploadImageAndUpdateProfile(imageUri, user, firestore, profilePictureUrl)
            }
        }
    }

    LaunchedEffect(currentUser) {
        currentUser?.let {
            fetchUserData(it, firestore, userData)
            // Fetch the profile picture URL from Firebase Storage
            fetchProfilePictureUrl(it.uid, profilePictureUrl)
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
            // Display profile picture if available
            profilePictureUrl.value?.let { imageUrl ->
                ProfilePicture(imageUrl)
            }

            IconButton(
                onClick = {
                    // Launch the image picker
                    imagePickerLauncher.launch("image/*")
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture"
                )
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
fun ProfilePicture(imageUrl: String) {
    // Load the profile picture using Coil's rememberImagePainter
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            // You can add any necessary transformations here
        }
    )

    Image(
        painter = painter,
        contentDescription = "Profile Picture",
        modifier = Modifier.size(120.dp),
        contentScale = ContentScale.Crop
    )
}

// Function to upload image to Firebase Storage and update user profile
private fun uploadImageAndUpdateProfile(imageUri: Uri, currentUser: FirebaseUser, firestore: FirebaseFirestore, profilePictureUrl: MutableState<String?>) {
    val storageRef = Firebase.storage.reference.child("profile_pictures/${currentUser.uid}.jpg")
    val uploadTask = storageRef.putFile(imageUri)

    uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        storageRef.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
            // Save the download URL to the user's profile in Firestore
            val userDocRef = firestore.collection("users").document(currentUser.uid)
            userDocRef.update("profilePictureUrl", downloadUri.toString())
                .addOnSuccessListener {
                    // Profile picture URL successfully updated in Firestore
                    profilePictureUrl.value = downloadUri.toString() // Update the profile picture URL state
                    Log.d("UploadSuccess", "Profile picture URL successfully updated: ${downloadUri.toString()}")
                }
                .addOnFailureListener { e ->
                    // Handle failure
                    Log.e("UploadFailure", "Failed to update profile picture URL", e)
                }
        } else {
            // Handle unsuccessful upload
            Log.e("UploadFailure", "Failed to upload image to Firebase Storage")
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

// Function to fetch profile picture URL from Firebase Storage
private suspend fun fetchProfilePictureUrl(userId: String, profilePictureUrl: MutableState<String?>) {
    try {
        val storageRef = Firebase.storage.reference.child("profile_pictures/$userId.jpg")
        val downloadUrl = storageRef.downloadUrl.await()
        profilePictureUrl.value = downloadUrl.toString()
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

