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
import androidx.compose.ui.Alignment

@Composable
fun PubMedicalHistoryPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HistoryProfileSection()
    }
}

@Composable
fun HistoryProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Medical History",
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
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Full Name",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(end = 50.dp)
            )
            Text(
                text = "Phone Number",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(end = 50.dp)
            )
            Text(
                text = "Gender",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sheila Ng",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .padding(end = 65.dp)
            )
            Text(
                text = "97864532",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .padding(end = 65.dp)
            )
            Text(
                text = "Female",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                )
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
                text = "Symptoms",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 45.dp)
        ){
            Text(
                text = "Asthma, Anxiety",
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
                text = "Currently on any medication?",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 45.dp)
        ){
            Text(
                text = "Anxiety pills",
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
                text = "Allergies",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 45.dp)
        ){
            Text(
                text = "Nil",
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
                text = "Smoke?",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 45.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = "Never",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                )
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
                text = "Drink alcohol?",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = "Occasionally",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                )
            )
        }
    }
}