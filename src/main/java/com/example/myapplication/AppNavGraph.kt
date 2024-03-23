package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screens.AdminBooking
import com.example.myapplication.screens.ChatScreen
import com.example.myapplication.screens.HealthReport
import com.example.myapplication.screens.PublicBooking
import com.example.myapplication.screens.RequestService
import com.example.myapplication.screens.UserProfileScreen1

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Report.route) {
            HealthReport(navController)
        }
        composable(route = Screen.Services.route){
            RequestService(navController)
        }
        composable(route = Screen.Chat.route) {
            ChatScreen(navController)
        }
        composable(route = Screen.Account.route) {
            UserProfileScreen1(navController)
        }
        composable(route = Screen.PublicBooking.route){ backStackEntry ->
            // Obtain the ViewModel scoped to this navigation destination
            val viewModel: DoctorListViewModel = viewModel(backStackEntry)

            // Now you can pass this viewModel to your PublicBooking composable
            PublicBooking(navController = navController)
        }
        composable(route = Screen.AdminBooking.route) {
            AdminBooking(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Signup.route) {
            SignupScreen(navController = navController)
        }
    }
}
