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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PubMedicinePrescripPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MedicineProfileSection()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun MedicineProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.pill),
            contentDescription = null,
            modifier = Modifier
                .height(140.dp)
                .clip(MaterialTheme.shapes.large)
                .padding(bottom = 32.dp)
        )
        Text(
            text = "Medicine Prescription",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 45.dp),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = "Medicine Name:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ){
            Text(
                text = "Dosage:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ){
            Text(
                text = "Doctor's Notes:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp
                )
            )
        }

    }
}
