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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource

@Composable
fun PubHealthProfilePage(navController: NavController, email: String) {
    var healthProfileData by remember { mutableStateOf(HealthProfileData("","","","",""))}

    LaunchedEffect(Unit){
        val db = Firebase.firestore
        if(email.equals("paulchua123@gmail.com", ignoreCase = true)){
            val docRef = db.collection("healthProfileDatas").document("4dqahau9R8uX6fc7o4Jb")
            docRef.get()
                .addOnSuccessListener { document ->
                    if(document != null && document.exists()) {
                        val data = document.toObject(HealthProfileData::class.java)
                        if(data != null){
                            healthProfileData = data
                        }
                    } else {
                        Log.d("Firestore", "No such document")
                    }
                }
        }
        else{
            val docRef = db.collection("healthProfileDatas").document("uLKIxl3F96nCXi6AzmAq")
            docRef.get()
                .addOnSuccessListener { document ->
                    if(document != null && document.exists()) {
                        val data = document.toObject(HealthProfileData::class.java)
                        if(data != null){
                            healthProfileData = data
                        }
                    } else {
                        Log.d("Firestore", "No such document")
                    }
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
                        .clickable { navController.navigate("pubHome/$email") }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 20.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .clickable { navController.navigate("pubHome/$email")}
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
                        .clickable { navController.navigate("pubHealthProfile/$email") }
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
        HealthProfileSection(healthProfileData)
        Spacer(modifier = Modifier.height(16.dp))
        MedicalHistoryButton(navController, email)
    }
}

@Composable
fun HealthProfileSection(healthProfileData: HealthProfileData) {
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
        Text (
            text = healthProfileData.name,
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
                text = healthProfileData.bloodType,
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
                text = healthProfileData.height,
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
                text = healthProfileData.weightValue,
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
                    text = healthProfileData.bloodPressureLevel,
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
fun MedicalHistoryButton(navController: NavController, email: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {navController.navigate("pubHistoryProfile/$email")},
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
