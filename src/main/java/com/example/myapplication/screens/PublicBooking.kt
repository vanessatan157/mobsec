package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.material3.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.DoctorListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.forEach
import java.util.Calendar

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PublicBooking(navController: NavController) {

    var Doctorexpanded by remember { mutableStateOf(false) }
    var Categoryexpanded by remember { mutableStateOf(false)}
    var selectedDate by remember { mutableStateOf("") } // To store the selected date
    val context = LocalContext.current
    var selectedTime by remember { mutableStateOf("") }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var selectedDoctorName by remember { mutableStateOf("Select a Doctor") }
    var selectedCategory by remember { mutableStateOf("Select Category")}


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GreetingSection()
            ScheduleAppointmentSection()

            Button(
                onClick = { Doctorexpanded = !Doctorexpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(60.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedDoctorName,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
            DropdownMenu(
                expanded = Doctorexpanded,
                onDismissRequest = { Doctorexpanded = false },
                properties = PopupProperties(focusable = false),
                modifier = Modifier.fillMaxWidth()
            ) {
                val doctorNames = listOf("Dr. Sue", "Dr. Anne", "Dr. Alan", "Dr. Joe", "Dr. Hermit")
                doctorNames.forEach { doctorName ->
                    DropdownMenuItem(
                        text = {Text(doctorName)},
                        onClick = {
                        selectedDoctorName = doctorName
                        Doctorexpanded = false
                    })
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    val calendar = Calendar.getInstance()
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)

                    DatePickerDialog(
                        context,
                        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                            selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                        },
                        year,
                        month,
                        day
                    ).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(60.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(), // Fill the width of the button
                    horizontalArrangement = Arrangement.SpaceBetween, // Space items out between start and end
                    verticalAlignment = Alignment.CenterVertically // Center items vertically
                ) {
                    Text(
                        text = if (selectedDate.isNotEmpty()) selectedDate else "Select Date",
                        modifier = Modifier
                            .weight(2f) // Causes the Text to occupy the maximum available space
                            .padding(start = 8.dp),
                        style = TextStyle(fontSize = 15.sp),
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown, // Use any icon you prefer
                        contentDescription = "Dropdown",
                        modifier = Modifier.size(26.dp) // Adjust icon size as needed
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {
                    val calendar = Calendar.getInstance()

                    TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            // Format time in a 24-hour format
                            selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true // Use 24-hour format
                    ).show()
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = if (selectedTime.isNotEmpty()) selectedTime else "Select Time",
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 8.dp),
                    style = TextStyle(fontSize = 15.sp),
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { Categoryexpanded = !Categoryexpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(60.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(), // Fill the width of the button
                    horizontalArrangement = Arrangement.SpaceBetween, // Space items out between start and end
                    verticalAlignment = Alignment.CenterVertically // Center items vertically
                ) {
                    Text(
                        text = selectedCategory,
                        style = TextStyle(fontSize = 15.sp),
                        modifier = Modifier
                            .weight(2f) // Causes the Text to occupy the maximum available space
                            .padding(start = 8.dp), // Padding to ensure the text does not stick to the edge
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown, // Use any icon you prefer
                        contentDescription = "Dropdown",
                        modifier = Modifier.size(26.dp) // Adjust icon size as needed
                    )
                }
            }
            DropdownMenu(
                expanded = Categoryexpanded,
                onDismissRequest = { Categoryexpanded = false },
                properties = PopupProperties(focusable = false),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Correctly creating a list of strings for doctor names
                val Categories = listOf("General Check-up", "Specialist Consultation", "Diagnostic Test", "Follow-up Appointment", "Major Surgery", "Minor Surgery", "Emergency Care", "Vaccination", "Pediatric", "Maternity and Prenatal Care", "Physiotherapy", "Mental Health Check-ups", "Palliative Care Consultations")
                Categories.forEach { category ->
                    DropdownMenuItem(
                        text = {Text(category)},
                        onClick = {
                            selectedCategory = category
                            Categoryexpanded = false
                        })
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            var openAlertDialog by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        // Correctly toggle the dialog's visibility state
                        openAlertDialog = true
                    },
                    modifier = Modifier
                        .width(200.dp) // Adjusted for better usability
                        .height(60.dp)
                ) {
                    Text(text = "Submit")
                }

                if (openAlertDialog) {
                    AlertDialog(
                        onDismissRequest = { openAlertDialog = false },
                        title = { Text("Booking Confirmed") },
                        text = { Text("Your schedule has been sent for confirmation, please check your appointment page.") },
                        confirmButton = {
                            Button(onClick = {
                                openAlertDialog = false
                                navController.navigate("home")
                            }) {
                                Text("OK")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingSection() {
    Text(
        "Welcome {user}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),

        style = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun ScheduleAppointmentSection() {
    Text(
        "Schedule Appointment",
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    )
}

@Preview
@Composable
fun PreviewMyApp() {
    PublicBooking(navController = rememberNavController())
}