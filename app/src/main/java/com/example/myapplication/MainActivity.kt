package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "signUp"){
                        composable("signUp"){
                            SignupScreen(navController)
                        }

                        composable("login"){
                            LoginScreen(navController)
                        }

                        composable("adminHome"){
                            AdminHomePage(navController)
                        }

                        composable("adminHealthProfile"){
                            AdminHealthProfilePage(navController)
                        }

                        composable("adminHistoryProfile"){
                            AdminMedicalHistoryPage(navController)
                        }

                        composable("adminMedicineProfile"){
                            AdminMedicinePrescripPage(navController)
                        }

                        composable("pubAppointmentPage"){
                            PubAppointmentPage(navController)
                        }

                        composable("pubHome/{email}"){
                            backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email")?:""
                            PubHomePage(navController, email)
                        }

                        composable("pubHealthProfile/{email}"){
                            backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email")?:""
                            PubHealthProfilePage(navController, email)
                        }

                        composable("pubHistoryProfile/{email}"){
                            backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email")?:""
                            PubMedicalHistoryPage(navController, email)
                        }

                        composable("pubMedicineProfile/{email}"){
                            backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email")?:""
                            PubMedicinePrescripPage(navController, email)
                        }
                    }
                }
            }
        }

        FirebaseApp.initializeApp(this)
    }
}


//@Preview(showBackground = true)
//@Composable
//fun MedicalAppContentPreview() {
//    MyApplicationTheme {
//        val dummyNavController = rememberNavController()
//        AdminHomePage(dummyNavController)
//    }
//}
