@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PublicBooking() {

    var isDoctorListExpanded by remember { mutableStateOf(false)}

    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp, start = 10.dp, end = 10.dp)
        ) {
            GreetingSection()
            ScheduleAppointmentSection()
            Button(onClick = { isDoctorListExpanded = true }, modifier = Modifier.fillMaxWidth()) {
                BasicText(text = "Doctor Name")
            }
            if (isDoctorListExpanded) {
                    DoctorList()
                }
            Button(onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth()) {
                BasicText(text = "Date")
            }
            Button(onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth()) {
                BasicText(text = "Time")
            }
            Button(onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth()) {
                BasicText(text = "Category of Visit")
            }
            Button(onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth()) {
                BasicText(text = "Submit")
            }
        }
    }
}

@Composable
fun GreetingSection() {
    BasicText(
        text = "Welcome, user!",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )
}

@Composable
fun ScheduleAppointmentSection() {
    BasicText(
        text = "Schedule Appointment",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    )
}

//function for Doctor Name button
@Composable
fun DoctorList() {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var doctorName by remember {
        mutableStateOf("")
    }

    Box{
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange ={ isExpanded = it}
        ) {
            TextField(
                value = doctorName,
                onValueChange = {},
                readOnly = true,
                // when expanded, it doesn't show the doctor names but when clicked on the
                //empty space, the Doctor's name selected
                // ie, expanded menu displays white drop down box, clicked on white space, "Dr.Anne" shows up
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { "Dr.Anne" },
                    onClick = {
                        doctorName = "Dr.Anne"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { "Dr.Sue" },
                    onClick = {
                        doctorName = "Dr.Sue"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { "Dr.David" },
                    onClick = {
                        doctorName = "Dr.David"
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { "Dr.John" },
                    onClick = {
                        doctorName = "Dr.John"
                        isExpanded = false
                    }
                )
            }

        }
    }
}


@Preview
@Composable
fun PreviewMyApp() {
    PublicBooking()
}