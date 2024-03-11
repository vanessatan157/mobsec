package com.example.myapplication

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.Alignment

@Composable
fun PubHealthProfilePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HealthProfileSection()
        Spacer(modifier = Modifier.height(16.dp))
        MedicalHistoryButton(navController)
    }
}

@Composable
fun HealthProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Text(
            text = "Health Profile",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )
        Text (
            text = "24/02/2024",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            textAlign = TextAlign.Center
        )
        Text (
            text = "Sheila Ng",
            style = MaterialTheme.typography.bodyLarge.copy(
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
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Blood Type:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 23.sp
                ),
                modifier = Modifier.padding(end = 32.dp),
            )
            Text(
                text = "A",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 23.sp
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Height:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 23.sp
                ),
                modifier = Modifier.padding(end = 32.dp)
                )
            Text (
                text = "160cm",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 23.sp
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Weight",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 23.sp
                ),
                modifier = Modifier.padding(end = 32.dp)
            )
            Text(
                text = "63kg",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 23.sp
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "BMI",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 23.sp
                ),
                modifier = Modifier.padding(end = 10.dp)
            )
            LinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier
                    .height(18.dp)
                    .width(150.dp)
                    .padding(end = 10.dp),
                color = Color(android.graphics.Color.parseColor("#B106A6"))
            )
            Text(
                text = "50%",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 23.sp
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .padding(end = 10.dp)
            ) {
                CircularProgressIndicator(
                    progress = 1f,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 10.dp),
                    color = Color(android.graphics.Color.parseColor("#E8E8E8")),
                    strokeWidth = 16.dp
                )

                CircularProgressIndicator(
                    progress = 0.25f, // Set the actual progress to 25%
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 10.dp),
                    color = Color(android.graphics.Color.parseColor("#B106A6")),
                    strokeWidth = 16.dp // Set the thickness of the circular progress bar
                )
                Text(
                    text = "119",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = "Blood Pressure Level:",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
                Text(
                    text = "Normal",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Composable
fun MedicalHistoryButton(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {navController.navigate("pubHistoryProfile")},
            modifier = Modifier
                .height(45.dp)
                .width(180.dp),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#04A6FF")))
        ) {
            Text(
                text = "Medical History",
                fontSize = 16.sp
            )
        }
    }
}
