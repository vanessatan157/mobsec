package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route =  "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Report: BottomBarScreen(
        route =  "report",
        title = "Report",
        icon = Icons.Default.Assessment
    )
    object Add: BottomBarScreen(
        route =  "Add",
        title = "Add",
        icon = Icons.Default.Add
    )
    object Chat: BottomBarScreen(
        route =  "chat",
        title = "Chat",
        icon = Icons.Default.Chat
    )
    object Account: BottomBarScreen(
        route =  "userProfile",
        title = "Account",
        icon = Icons.Default.AccountCircle
    )
}