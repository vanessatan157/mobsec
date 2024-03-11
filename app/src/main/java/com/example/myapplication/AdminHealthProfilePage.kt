package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource

@Composable
fun AdminHealthProfilePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AdminHealthProfileSection(navController)
        Spacer(modifier = Modifier.height(16.dp))
        AdminMedicalHistoryButton(navController)
    }
}

@Composable
fun AdminHealthProfileSection(navController: NavController) {
    var name by remember {mutableStateOf(" ")}
    var bloodType by remember { mutableStateOf(" ") }
    var height by remember { mutableStateOf(" ") }
    var weightValue by remember { mutableStateOf(" ") }
    var bloodPressureLevel by remember { mutableStateOf(" ") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
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
        OutlinedTextField (
            value = name,
            onValueChange = {name = it},
            label = {Text("Full Name")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
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
            OutlinedTextField(
                value = bloodType,
                onValueChange = {bloodType = it},
                label = {Text("Blood Type")},
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
            OutlinedTextField (
                value = height,
                onValueChange = {height = it},
                label = {Text("Height Value")}
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
            OutlinedTextField(
                value = weightValue,
                onValueChange = {weightValue = it},
                label = {Text("Weight Value")}
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
            OutlinedTextField(
                value = bloodPressureLevel,
                onValueChange = {bloodPressureLevel = it},
                label = {Text("Blood Pressure Level")},
                modifier = Modifier
                    .width(150.dp)
                    .padding(end = 16.dp)
                )
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

@Composable
fun AdminMedicalHistoryButton(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {navController.navigate("adminHistoryProfile")},
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
