package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun EditProfileScreen(navController: NavController, initialUserData: UserData? = null) {
    // State for user input fields
    val userData = remember { mutableStateOf(initialUserData ?: UserData()) }

    val firestore = FirebaseFirestore.getInstance()
    val currentUser = FirebaseAuth.getInstance().currentUser

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Edit Profile screen UI components
            Text("Edit Profile")

            Spacer(modifier = Modifier.height(16.dp))

            // Input fields for editing profile
            EditProfileTextField(label = "Username", value = userData.value.username) { newValue ->
                userData.value = userData.value.copy(username = newValue)
            }
            EditProfileTextField(label = "Email", value = userData.value.email) { newValue ->
                userData.value = userData.value.copy(email = newValue)
            }
            EditProfileTextField(label = "Mobile", value = userData.value.mobileNumber) { newValue ->
                userData.value = userData.value.copy(mobileNumber = newValue)
            }
            EditProfileTextField(label = "Gender", value = userData.value.gender) { newValue ->
                userData.value = userData.value.copy(gender = newValue)
            }
            EditProfileTextField(label = "Age", value = userData.value.age) { newValue ->
                userData.value = userData.value.copy(age = newValue)
            }

            // Save button
            Button(
                onClick = {
                    val updatedUserData = mapOf(
                        "username" to userData.value.username,
                        "email" to userData.value.email,
                        "mobileNumber" to userData.value.mobileNumber,
                        "gender" to userData.value.gender,
                        "age" to userData.value.age
                    )

                    // Update user data in Firestore
                    currentUser?.let { user ->
                        firestore.collection("users").document(user.uid)
                            .update(updatedUserData)
                            .addOnSuccessListener {
                                // Successfully updated data
                                navController.popBackStack()
                            }
                            .addOnFailureListener { e ->
                                // Handle failure
                                // You can show an error message or perform any other action here
                                e.printStackTrace()
                            }
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Save")
            }
        }
    }
}


data class UserData(
    val username: String = "",
    val email: String = "",
    val mobileNumber: String = "",
    val gender: String = "",
    val age: String = ""
)

@Composable
fun EditProfileTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = label)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Perform action when Done button is pressed */ }
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    EditProfileScreen(dummyNavController)
}
