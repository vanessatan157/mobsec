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
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AdminHealthProfilePage(navController: NavController) {
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
                        .padding(start = 20.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .clickable { navController.navigate("adminHome")}
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AdminHealthProfileSection(navController)
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
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 32.dp),
//            horizontalArrangement = Arrangement.Center
//        ){
//            Text(
//                text = "BMI",
//                style = MaterialTheme.typography.bodyMedium.copy(
//                    fontSize = 23.sp
//                ),
//                modifier = Modifier.padding(end = 10.dp)
//            )
//            LinearProgressIndicator(
//                progress = 0.5f,
//                modifier = Modifier
//                    .height(18.dp)
//                    .width(150.dp)
//                    .padding(end = 10.dp),
//                color = Color(android.graphics.Color.parseColor("#FF8CF6"))
//            )
//            Text(
//                text = "50%",
//                style = MaterialTheme.typography.bodyMedium.copy(
//                    fontSize = 23.sp
//                )
//            )
//        }
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
                        fontSize = 16.sp),
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
                Text(text = "Normal", style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            SubmitButton(name = name, bloodType = bloodType, height = height, weightValue = weightValue,
                bloodPressureLevel = bloodPressureLevel)
        }
    }
}

data class HealthProfileData(val name: String = "", val bloodType: String = "", val height: String = "",
    val weightValue: String = "", val bloodPressureLevel: String = "")

@Composable
fun SubmitButton(name: String, bloodType: String, height: String, weightValue: String,
                 bloodPressureLevel: String
){
    val fireStore = FirebaseFirestore.getInstance()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Button(
            onClick = {
                val healthProfileData = HealthProfileData(name = name, bloodType = bloodType, height = height,
                    weightValue = weightValue, bloodPressureLevel = bloodPressureLevel)

                fireStore.collection("healthProfileDatas")
                    .add(healthProfileData)
                    .addOnSuccessListener {Log.d("Firestore", "Health profile added with ID: ${it.id}")}
            },
            modifier = Modifier
                .height(45.dp)
                .width(180.dp),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#04A6FF")))
        ){
            Text(text = "Submit", fontSize = 16.sp)
        }
    }
}

@Composable
fun AdminMedicalHistoryButton(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {navController.navigate("adminHistoryProfile")},
            modifier = Modifier
                .height(45.dp)
                .width(180.dp),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#04A6FF")))
        ) {
            Text(text = "Medical History", fontSize = 16.sp)
        }
    }
}
