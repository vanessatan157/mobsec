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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun PubMedicalHistoryPage(navController: NavController, email: String) {

    var medicalHistoryData by remember { mutableStateOf(MedicalHistoryData("", "", "",
    "", "", "", "", "")) }

    LaunchedEffect(Unit){
        val db = Firebase.firestore
        if(email.equals("paulchua123@gmail.com", ignoreCase = true)){
            val docRef = db.collection("healthProfileDatas").document("nDGGWfkDdmZTOcTc2B9Z")

            docRef.get()
                .addOnSuccessListener { document ->
                    if(document != null && document.exists()) {
                        val data = document.toObject(MedicalHistoryData::class.java)
                        if(data != null){
                            medicalHistoryData = data
                        }
                        else{
                            Log.d("Firestore", "No such document")
                        }
                    }
                }
        }
        else{
            val docRef = db.collection("healthProfileDatas").document("Or5cXm6z59RnS0mhBOLN")

            docRef.get()
                .addOnSuccessListener { document ->
                    if(document != null && document.exists()) {
                        val data = document.toObject(MedicalHistoryData::class.java)
                        if(data != null){
                            medicalHistoryData = data
                        }
                        else{
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
                        .clickable { navController.navigate("pubHealthProfile/$email") }
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
        HistoryProfileSection(medicalHistoryData)
    }
}

@Composable
fun HistoryProfileSection(medicalHistoryData: MedicalHistoryData) {
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
                text = medicalHistoryData.fullName,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .padding(end = 65.dp)
            )
            Text(
                text = medicalHistoryData.phoneNumber,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .padding(end = 65.dp)
            )
            Text(
                text = medicalHistoryData.gender,
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
                text = "IC",
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
                text = medicalHistoryData.icNumber,
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
                text = "Address",
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
                text = medicalHistoryData.address,
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
                .padding(bottom = 30.dp)
        ){
            Text(
                text = medicalHistoryData.symptoms,
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
                .padding(bottom = 30.dp)
        ){
            Text(
                text = medicalHistoryData.medication,
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
                .padding(bottom = 30.dp)
        ){
            Text(
                text = medicalHistoryData.allergies,
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
                .padding(bottom = 30.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = medicalHistoryData.smoke,
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
                text = medicalHistoryData.alcohol,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                )
            )
        }
    }
}