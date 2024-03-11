package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color

@Composable
fun AdminMedicalHistoryPage(navController: NavController) {
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
    var gender by remember { mutableStateOf(" ") }
    var symptoms by remember { mutableStateOf(" ") }
    var medication by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf(" ") }

    val options = listOf("Yes", "No")
    val options2 = listOf("Frequently", "Occasionally", "Never")
    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Select") }
    var selectedOption2 by remember { mutableStateOf("Select") }

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
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                modifier = Modifier
                    .width(150.dp)
                    .padding(end = 50.dp)
            )
            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                modifier = Modifier
                    .width(150.dp)
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
                .padding(bottom = 45.dp)
        ) {
            OutlinedTextField(
                value = symptoms,
                onValueChange = { symptoms = it },
                modifier = Modifier
                    .width(175.dp)
                    .padding(start = 20.dp)
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
                .padding(bottom = 45.dp)
        ) {
            OutlinedTextField(
                value = medication,
                onValueChange = { medication = it },
                modifier = Modifier
                    .width(175.dp)
                    .padding(start = 20.dp)
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
                .padding(bottom = 45.dp)
        ) {
            OutlinedTextField(
                value = allergies,
                onValueChange = { allergies = it },
                modifier = Modifier
                    .width(175.dp)
                    .padding(start = 20.dp)
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
                .padding(bottom = 45.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 25.dp)
                    .width(100.dp)
                    .height(40.dp)
                    .background(Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { expanded = !expanded },
                contentAlignment = Alignment.Center
            ) {
                Text(text = selectedOption ?: "Select")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { option ->
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        selectedOption = option
                        expanded = false
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
                        .clickable { expanded2 = !expanded2 },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = selectedOption2 ?: "Select")
                }
                DropdownMenu(
                    expanded = expanded2,
                    onDismissRequest = { expanded2 = false },
                ) {
                    options2.forEach { option ->
                        DropdownMenuItem(text = { Text(text = option) }, onClick = {
                            selectedOption2 = option
                            expanded2 = false
                        })
                    }
                }
            }
        }
    }