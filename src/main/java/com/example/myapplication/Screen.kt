package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.BookOnline
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Web
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: Screen(
        route =  "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Report: Screen(
        route =  "report",
        title = "Report",
        icon = Icons.Default.Assessment
    )
    object Services: Screen(
        route =  "services",
        title = "Services",
        icon = Icons.Default.Add
    )
    object Chat: Screen(
        route =  "chat",
        title = "Chat",
        icon = Icons.Default.Chat
    )
    object Account: Screen(
        route =  "userProfile",
        title = "Account",
        icon = Icons.Default.AccountCircle
    )
    object PublicBooking: Screen(
        route = "publicBooking",
        title = "Booking",
        icon = Icons.Default.BookOnline
    )
    object AdminBooking: Screen(
        route = "adminBooking",
        title = "AdminBook",
        icon = Icons.Default.Web
    )
    object Login: Screen(
        route = "loginScreen",
        title = "Login",
        icon = Icons.Default.Login
    )
    object Signup: Screen(
        route = "signupScreen",
        title = "Sign Up",
        icon = Icons.Default.Create
    )
}