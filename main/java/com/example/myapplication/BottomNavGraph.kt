package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screens.BookingScreen
import com.example.myapplication.screens.ChatScreen
import com.example.myapplication.screens.HealthReport
import com.example.myapplication.screens.UserProfileScreen1

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Report.route) {
            HealthReport(navController)
        }
        composable(route = BottomBarScreen.Add.route){
            BookingScreen(navController)
        }
        composable(route = BottomBarScreen.Chat.route) {
            ChatScreen(navController)
        }
        composable(route = BottomBarScreen.Account.route) {
            UserProfileScreen1(navController)
        }
    }
}
