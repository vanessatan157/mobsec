package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PublicBooking() {
    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp, start = 10.dp, end = 10.dp)
        ) {
            GreetingSection()
            ScheduleAppointmentSection()
            Button(onClick = { /* Do something */ }, modifier = Modifier.fillMaxWidth()) {
                BasicText(text = "Doctor Name")
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

@Preview
@Composable
fun PreviewMyApp() {
    PublicBooking()
}