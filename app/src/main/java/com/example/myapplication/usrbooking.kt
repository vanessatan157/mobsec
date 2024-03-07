<<<<<<< Updated upstream
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.CanFocusChecker.start
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.NonDisposableHandle.parent
=======
package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
>>>>>>> Stashed changes

@Composable
fun usrbooking() {
    Surface(color = Color.White) {
<<<<<<< Updated upstream
        ConstraintLayoutContent()
=======
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
>>>>>>> Stashed changes
    }
}

@Composable
<<<<<<< Updated upstream
fun ConstraintLayoutContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp, start = 10.dp, end = 10.dp)
    ) {
        val (tvUserGreeting, tvSchedAppointment, btnDoctorName, btnDate, btnTime, btnCatVisit, btnSubmit) = createRefs()

        BasicText(
            text = "Welcome, user!",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.constrainAs(tvUserGreeting) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        BasicText(
            text = "Schedule Appointment",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.constrainAs(tvSchedAppointment) {
                top.linkTo(tvUserGreeting.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier
                .constrainAs(btnDoctorName) {
                    top.linkTo(tvSchedAppointment.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
        ) {
            BasicText(text = "Doctor Name")
        }

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier
                .constrainAs(btnDate) {
                    top.linkTo(btnDoctorName.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
        ) {
            BasicText(text = "Date")
        }

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier
                .constrainAs(btnTime) {
                    top.linkTo(btnDate.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
        ) {
            BasicText(text = "Time")
        }

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier
                .constrainAs(btnCatVisit) {
                    top.linkTo(btnTime.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
        ) {
            BasicText(text = "Category of Visit")
        }

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier
                .constrainAs(btnSubmit) {
                    top.linkTo(btnCatVisit.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        ) {
            BasicText(text = "Submit")
        }
    }
}

=======
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

@Composable
fun DoctorName() {
    val doctorName = listOf(
        "Alex Pang",
        "David Lee",
        "Marianna Rodriogo",
        "Chen Qin Lin",
        "Adrianna Sumpay"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        DropdownMenu(
            items = doctorName.toList(),
            onItemSelected = {
                Log.v("item", it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            )

    }
}


>>>>>>> Stashed changes
@Preview
@Composable
fun PreviewMyApp() {
    usrbooking()
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
