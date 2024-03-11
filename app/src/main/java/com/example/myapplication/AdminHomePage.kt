package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold

val purpleColor = Color(android.graphics.Color.parseColor("#B106A6"))

@Composable
fun AdminHomePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MedicalAppHome(navController)
        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Composable
fun MedicalAppHome(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Text(
            text = "Medical App",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )
        Text (
            text = "Current Appointments",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 30.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp)
                .then(Modifier.background(color = purpleColor)),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Sheila Ng",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(end = 150.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.medicine),
                contentDescription = "Medicine Prescription",
                modifier = Modifier
                    .clickable {
                        navController.navigate("adminMedicineProfile")
                    }
                    .height(30.dp)
                    .clip(MaterialTheme.shapes.small)
                    .padding(end = 16.dp)
            )
            /*Image (
                painter = painterResource(id = R.drawable.note),
                contentDescription = "Health Profile",
                modifier = Modifier
                    .clickable {
                        navController.navigate("adminHealthProfile")
                    }
                    .height(30.dp)
                    .clip(MaterialTheme.shapes.medium)
            )*/
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
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .clickable { }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 85.dp)
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
                        .clickable {  }
                        .height(50.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 10.dp)
                )
            }
        }
    ){

    }

}