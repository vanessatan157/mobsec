package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RequestService(navController: NavController){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            "Request Services",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
                verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            // Logout button in the top right corner
            IconButton(
                onClick = {
                    navController.navigate("publicBooking")
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(280.dp)
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Text(
                    text = "Book Appointment",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            IconButton(
                onClick = {
                    // Navigate to the login screen
                    navController.navigate("ChangeBooking")
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(280.dp)
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Text(
                    text = "Modify/Change Appointment",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold // Set the font weight to bold
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            IconButton(
                onClick = {
                    // Navigate to the login screen
                    navController.navigate("MedicalPrescription")
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(280.dp)
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Text(
                    text = "Request for Extra Medications",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold // Set the font weight to bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RequestPreview() {
    // For the preview, you can pass a dummy NavController
    val dummyNavController = rememberNavController()
    RequestService(dummyNavController)
}