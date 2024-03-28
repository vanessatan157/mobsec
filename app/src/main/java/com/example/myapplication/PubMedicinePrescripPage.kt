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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color


@Composable
fun PubMedicinePrescripPage(navController: NavController, email: String) {

    var medicinePrescripData by remember { mutableStateOf(MedicinePrescripData("", "",
        "")) }

    LaunchedEffect(Unit){
        val db = Firebase.firestore

        if(email.equals("paulchua123@gmail.com", ignoreCase = true)){
            val docRef = db.collection("healthProfileDatas").document("E2v0x16EIMYiLFxYeu8k")

            docRef.get()
                .addOnSuccessListener { document ->
                    if(document != null && document.exists()) {
                        val data = document.toObject(MedicinePrescripData::class.java)
                        if(data != null){
                            medicinePrescripData = data
                        }
                        else {
                            Log.d("Firestore", "No such document")
                        }
                    }
                }
        }
        else{
            val docRef = db.collection("healthProfileDatas").document("jJjxaki5xV1N88cOhitG")

            docRef.get()
                .addOnSuccessListener { document ->
                    if(document != null && document.exists()) {
                        val data = document.toObject(MedicinePrescripData::class.java)
                        if(data != null){
                            medicinePrescripData = data
                        }
                        else {
                            Log.d("Firestore", "No such document")
                        }
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
        MedicineProfileSection(medicinePrescripData)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun MedicineProfileSection(medicinePrescripData: MedicinePrescripData) {
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
                    .padding(end = 16.dp)
            )
            Text(
                text = medicinePrescripData.medicineName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp
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
                ),
                modifier = Modifier
                    .padding(end = 16.dp)
            )
            Text(
                text = medicinePrescripData.dosage,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp
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
                ),
                modifier = Modifier
                    .padding(end = 16.dp)
            )
            Text(
                text = medicinePrescripData.doctorNotes,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp
                )
            )
        }

    }
}
