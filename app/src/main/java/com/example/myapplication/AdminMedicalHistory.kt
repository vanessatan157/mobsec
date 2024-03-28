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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import android.util.Log
import androidx.compose.material3.ButtonDefaults
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AdminMedicalHistoryPage(navController: NavController) {
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
                        .clickable { navController.navigate("adminHealthProfile") }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 20.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .clickable { navController.navigate("adminHistoryProfile")}
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
        AdminHistoryProfileSection()
    }
}

@Composable
fun AdminHistoryProfileSection() {
    var fullName by remember { mutableStateOf(" ") }
    var phoneNumber by remember { mutableStateOf(" ") }
    var symptoms by remember { mutableStateOf(" ") }
    var medication by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf(" ") }
    var icNumber by remember { mutableStateOf(" ") }
    var address by remember { mutableStateOf("") }

    val smokeOptions = listOf("Yes", "No")
    val drinkOptions = listOf("Frequently", "Occasionally", "Never")
    val genderOptions = listOf("Male", "Female")
    var smokeExpanded by remember { mutableStateOf(false) }
    var drinkExpanded by remember { mutableStateOf(false) }
    var genderExpanded by remember { mutableStateOf(false) }
    var smokeSelect by remember { mutableStateOf("Select") }
    var drinkSelect by remember { mutableStateOf("Select") }
    var genderSelect by remember { mutableStateOf("Select") }

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
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier
                    .width(150.dp)
                    .padding(end = 50.dp)
                    .height(50.dp)
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                modifier = Modifier
                    .width(150.dp)
                    .padding(end = 50.dp)
                    .height(50.dp)
            )
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
                    .background(Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { genderExpanded = !genderExpanded },
                contentAlignment = Alignment.Center
            ) {
                Text(text = genderSelect ?: "Select")
            }
            DropdownMenu(
                expanded = genderExpanded,
                onDismissRequest = { genderExpanded = false }
            ) {
                genderOptions.forEach { option ->
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        genderSelect = option
                        genderExpanded = false
                    })
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ){
            Text(
                modifier =  Modifier
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
                .padding(bottom = 25.dp)
        ){
            OutlinedTextField(
                value = icNumber,
                onValueChange = { icNumber = it },
                modifier = Modifier
                    .width(175.dp)
                    .padding(start = 20.dp)
                    .height(50.dp)
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
                .padding(bottom = 15.dp)
        ) {
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                modifier = Modifier
                    .width(175.dp)
                    .padding(start = 20.dp)
                    .height(50.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
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
                .padding(bottom = 15.dp)
        ) {
            OutlinedTextField(
                value = symptoms,
                onValueChange = { symptoms = it },
                modifier = Modifier
                    .width(175.dp)
                    .padding(start = 20.dp)
                    .height(50.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
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
                .padding(bottom = 15.dp)
        ) {
            OutlinedTextField(
                value = medication,
                onValueChange = { medication = it },
                modifier = Modifier
                    .width(175.dp)
                    .padding(start = 20.dp)
                    .height(50.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
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
                .padding(bottom = 15.dp)
        ) {
            OutlinedTextField(
                value = allergies,
                onValueChange = { allergies = it },
                modifier = Modifier
                    .width(175.dp)
                    .padding(start = 20.dp)
                    .height(50.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
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
                .padding(bottom = 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 25.dp)
                    .width(100.dp)
                    .height(40.dp)
                    .background(Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { smokeExpanded = !smokeExpanded },
                contentAlignment = Alignment.Center
            ) {
                Text(text = smokeSelect ?: "Select")
            }
            DropdownMenu(
                expanded = smokeExpanded,
                onDismissRequest = { smokeExpanded = false },
            ) {
                smokeOptions.forEach { option ->
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        smokeSelect = option
                        smokeExpanded = false
                    })
                }
            }
        }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
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
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .width(100.dp)
                        .height(40.dp)
                        .background(Color(android.graphics.Color.parseColor("#F0F0F0")))
                        .clickable { drinkExpanded = !drinkExpanded },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = drinkSelect ?: "Select")
                }
                DropdownMenu(
                    expanded = drinkExpanded,
                    onDismissRequest = { drinkExpanded = false },
                    modifier = Modifier
                        .padding(end = 20.dp)
                ) {
                    drinkOptions.forEach { option ->
                        DropdownMenuItem(text = { Text(text = option) }, onClick = {
                            drinkSelect = option
                            drinkExpanded = false
                        })
                    }
                }

                SubmitButton(
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    gender = genderSelect,
                    symptoms = symptoms,
                    medication = medication,
                    allergies = allergies,
                    icNumber = icNumber,
                    address = address,
                    smoke = smokeSelect,
                    alcohol  = drinkSelect
                )
            }
        }
    }


data class MedicalHistoryData(val fullName: String = "", val phoneNumber: String = "", val gender: String = "",
    val symptoms: String = "", val medication: String = "", val allergies: String = "", val icNumber: String = "",
    val address: String = "", val smoke: String = "", val alcohol: String = "")

@Composable
fun SubmitButton(fullName: String, phoneNumber: String, gender: String, symptoms: String, medication: String,
allergies: String, icNumber: String, address: String, smoke: String, alcohol: String)
{
    val fireStore = FirebaseFirestore.getInstance()

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(
            onClick = {
                val medicalHistoryData = MedicalHistoryData(fullName = fullName, phoneNumber = phoneNumber,
                    gender = gender, symptoms = symptoms, medication = medication, allergies = allergies,
                    icNumber = icNumber, address = address, smoke = smoke, alcohol = alcohol)

                fireStore.collection("healthProfileDatas")
                    .add(medicalHistoryData)
                    .addOnSuccessListener { Log.d("Firestore", "Medical History added with ID: ${it.id}") }
                    .addOnFailureListener{ e -> Log.e("Firestore", "Error adding medical history item", e)}
            },
            modifier = Modifier
                .height(40.dp)
                .width(160.dp),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#04A6FF")))
        ){
            Text(
                text = "Submit",
                fontSize = 16.sp
            )
        }
    }
}

