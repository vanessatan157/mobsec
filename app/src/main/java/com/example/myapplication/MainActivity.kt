package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val smsReceiver = SmsReceiver()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                // Set up the NavHost with LoginScreen and SignupScreen
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("signup") {
                        SignupScreen(navController)
                    }
                    composable("home") {
                        HomeScreen(navController)
                    }
                }
            }
        }

        // Check permissions and register receiver
        if (hasPermissions()) {
            registerReceiver(smsReceiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
        } else {
            requestPermissions()
        }
    }

    //checks if relevant permission exists in app
    private fun hasPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    //for requesting user permission to allow Receive and Read Sms
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS),
            PERMISSION_REQUEST_CODE
        )
    }

    // for request permission handling
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // Permission granted, register the SMS receiver
                registerReceiver(smsReceiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
            } else {
                // Permission denied, handle accordingly
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister SMSReceiver
        unregisterReceiver(smsReceiver)
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 101 // Or any unique code
    }
}

//Not in use
//object CredentialsManager {
//
//    fun storeCredentials(context: Context, email: String, password: String) {
//        val prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
//        prefs.edit {
//            putString("email", email)
//            putString("password", password)
//        }
//    }
//
//    private inline fun SharedPreferences.edit(
//        commit: Boolean = false,
//        action: SharedPreferences.Editor.() -> Unit
//    ) {
//        val editor = edit()
//        action(editor)
//        if (commit) {
//            editor.commit()
//        } else {
//            editor.apply()
//        }
//    }
//}
