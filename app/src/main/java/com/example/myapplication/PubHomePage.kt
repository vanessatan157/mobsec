package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment

@Composable
fun PubHomePage(navController: NavController, email: String) {
    Scaffold(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth(),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .clickable { }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 95.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Medical Appointments",
                    modifier = Modifier
                        .clickable { }
                        .height(75.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.records),
                    contentDescription = "Health Profile",
                    modifier = Modifier
                        .clickable { navController.navigate("pubHealthProfile/$email") }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 10.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {  }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 10.dp)
                )
            }
        }
    ){

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PubMedicalAppHome(navController, email)
        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Composable
fun PubMedicalAppHome(navController: NavController, email: String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Text(
            text = "Medical App",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )
        Text (
            text = "Current Appointments",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .then(Modifier.background(color = Color(android.graphics.Color.parseColor("#FF8CF6")))),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { navController.navigate("pubAppointmentPage") },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "5/4/2024 11:00AM",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 165.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.medicine),
                    contentDescription = "Medicine Prescription",
                    modifier = Modifier
                        .clickable {
                            navController.navigate("pubMedicineProfile/$email")
                        }
                        .height(30.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 165.dp)
                )
            }
        }

        Text(
            text = "Incoming Appointments",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(color = Color(android.graphics.Color.parseColor("#FF8CF6"))),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { navController.navigate("pubAppointmentPage") },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "10/4/2024 10.30AM",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 150.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { navController.navigate("pubAppointmentPage") },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "15/4/2024 11.00AM",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 150.dp)
                )
            }
        }

    }

}