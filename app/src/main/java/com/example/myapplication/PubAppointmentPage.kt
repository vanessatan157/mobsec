package com.example.myapplication

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PubAppointmentPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AppointmentProfileSection()
    }
}

@Composable
fun AppointmentProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
    ) {
        Text(
            text = "Appointment",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = "Full Name",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(start = 25.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, start = 25.dp)
        ) {
            Text(
                text = "Sheila Ng",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .padding(end = 65.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = "Doctor's Name",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ){
            Text(
                text = "Mr Chua",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(start = 25.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = "Date",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ){
            Text(
                text = "11/3/2024",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(start = 25.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = "Time",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ){
            Text(
                text = "11am",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(start = 25.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = "Reason for visit",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ){
            Text(
                text = "Flu",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(start = 25.dp)
            )
        }
    }
}