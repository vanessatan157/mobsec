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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Composable
fun AdminMedicinePrescripPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AdminMedicineProfileSection(navController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun AdminMedicineProfileSection(navController: NavController) {
    var medicineName by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    var doctorNotes by remember { mutableStateOf("") }

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
                ),
                modifier = Modifier
                    .padding(end = 32.dp)
            )
            OutlinedTextField(
                value = medicineName,
                onValueChange = {medicineName = it},
                modifier = Modifier
                    .height(35.dp)
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
                ),
                modifier = Modifier
                    .padding(end = 32.dp)
            )
            OutlinedTextField(
                value = dosage,
                onValueChange = {dosage = it},
                modifier = Modifier
                    .height(35.dp)
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
                ),
                modifier = Modifier
                    .padding(end = 32.dp)
            )
            OutlinedTextField(
                value = doctorNotes,
                onValueChange = {doctorNotes = it}
            )
        }

    }
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
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable { navController.navigate("adminHome") }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 10.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .clickable { }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 24.dp)
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
                    contentDescription = "Medical History",
                    modifier = Modifier
                        .clickable { navController.navigate("adminHealthProfile") }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 10.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable { }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 10.dp)
                )
            }
        }
    ){

    }

}
