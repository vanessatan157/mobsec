package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

@Composable
fun ChatScreen(navController: NavController) {
    var selectedUsername by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }
    var messageText by remember { mutableStateOf("") }
    var chatMessages by remember { mutableStateOf(listOf<String>()) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Fetch the list of usernames from Firestore
    val userCollection = FirebaseFirestore.getInstance().collection("users")
    var userList by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    LaunchedEffect(key1 = true) {
        userCollection.get()
            .addOnSuccessListener { result ->
                val usernames = mutableListOf<String>()
                for (document in result) {
                    val username = document.getString("username")
                    username?.let {
                        usernames.add(it)
                    }
                }
                userList = usernames
            }
            .addOnFailureListener { e ->
                showToast = true
                toastMessage = "Error fetching usernames: ${e.message}"
            }
    }

    // Fetch the current user's data
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance() // Use FirebaseFirestore.getInstance() directly
    var userData by remember { mutableStateOf<MutableMap<String, String>>(mutableMapOf()) } // Change the type to MutableMap
    LaunchedEffect(currentUser) {
        currentUser?.let {
            fetchUserData(it, firestore) { fetchedUserData ->
                userData = fetchedUserData.toMutableMap() // Convert fetched data to mutable map
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Display the list of usernames
        UserList(userList = userList, selectedUsername = selectedUsername) { username ->
            selectedUsername = username
        }

        // Display chat messages
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(chatMessages) { message ->
                ChatMessage(text = message, isSentByCurrentUser = message.startsWith(userData["username"] ?: ""))
            }
        }

        // Input field and send button
        Surface(
            color = Color.LightGray,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                // Input field for typing messages
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    label = { Text("Type a message...") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        sendMessage(selectedUsername, messageText, userData)
                        messageText = ""
                        keyboardController?.hide()
                    }),
                    modifier = Modifier.weight(1f)
                )

                // Send button
                Button(
                    onClick = {
                        sendMessage(selectedUsername, messageText, userData)
                        messageText = ""
                        keyboardController?.hide()
                    },
                    enabled = messageText.isNotBlank()
                ) {
                    Text("Send")
                }
            }
        }
    }
}

fun sendMessage(recipientUsername: String, message: String, userData: Map<String, String>) {
    val firestore = FirebaseFirestore.getInstance()
    val chatCollection = firestore.collection("chat")

    // Retrieve the sender's username from userData map
    val senderUsername = userData["username"] ?: ""

    val messageMap = hashMapOf(
        "sender" to senderUsername,
        "recipient" to recipientUsername,
        "message" to message,
        "timestamp" to Date().toString()
    )
    chatCollection.add(messageMap)
}

@Composable
fun UserList(userList: List<String>, selectedUsername: String, onUsernameSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        userList.forEach { username ->
            Text(
                text = username,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onUsernameSelected(username)
                    }
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .background(
                        color = if (username == selectedUsername) Color.LightGray else Color.Transparent
                    )
            )
        }
    }
}

@Composable
fun ChatMessage(text: String, isSentByCurrentUser: Boolean) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = if (isSentByCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isSentByCurrentUser) Color.Blue else Color.Gray,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// Define fetchUserData function
suspend fun fetchUserData(currentUser: FirebaseUser, firestore: FirebaseFirestore, onUserDataFetched: (Map<String, String>) -> Unit) {
    val userDoc = firestore.collection("users").document(currentUser.uid)
    userDoc.get()
        .addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                val userData = document.data ?: mapOf()
                onUserDataFetched(userData as Map<String, String>)
            }
        }
        .addOnFailureListener { e ->
            // Handle error
        }
}
