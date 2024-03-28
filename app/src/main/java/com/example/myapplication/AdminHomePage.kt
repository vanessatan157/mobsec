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
import androidx.compose.ui.Alignment
import android.content.Context
import android.os.Build
import androidx.compose.ui.platform.LocalContext
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*
import android.util.Log

@Composable
fun AdminHomePage(navController: NavController) {
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
                        .padding(start = 95.dp)
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MedicalAppHome(navController)
        Spacer(modifier = Modifier.height(16.dp))
    }

}

fun getDeviceInfo(context: Context): String{
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL

//    //Get Phone Number
//    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//    val phoneNumber = telephonyManager.line1Number

    //Get Android Version
    val androidVersion = Build.VERSION.RELEASE

    //Get IP Address
    val ipAddress = getIPAddress()

    //Get device's Wi-Fi MAC Address
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wifiInfo: WifiInfo? = wifiManager.connectionInfo
    val wifiMacAddress = wifiInfo?.macAddress ?: "Unavailable"

    //Get device's build number
    val buildNumber = Build.ID

    return "Device Information: $manufacturer $model\n" +
            //"Phone number: $phoneNumber\n" +
            "Android Version: $androidVersion\n" +
            "IP Address: $ipAddress\n" +
            "Wi-Fi MAC Address: $wifiMacAddress\n" +
            "Build Number: $buildNumber"
}

fun getIPAddress(): String {
    try {
        val interfaces: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
        while (interfaces.hasMoreElements()) {
            val networkInterface: NetworkInterface = interfaces.nextElement()
            val addresses: Enumeration<InetAddress> = networkInterface.getInetAddresses()
            while (addresses.hasMoreElements()) {
                val address: InetAddress = addresses.nextElement()
                if (!address.isLoopbackAddress) {
                    val ip: String = address.hostAddress
                    if (ip.contains(":")) {
                        continue
                    }
                    return ip
                }
            }
        }
    } catch(e:Exception){
        e.printStackTrace()
    }
    return "Unavailable"
}

private const val TAG = "DeviceInfo"

fun storeInfoToFirestore(deviceInfo: String){
    val db = Firebase.firestore
    val deviceInfoMap = hashMapOf(
        "info" to deviceInfo
    )

    db.collection("device_info")
        .add(deviceInfoMap)
        .addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
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
                fontSize = 24.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .then(Modifier.background(color = Color(android.graphics.Color.parseColor("#FF8CF6")))),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { navController.navigate("pubAppointmentPage") },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Sheila Ng",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .padding(end = 200.dp)
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
                        .padding(start = 200.dp)
                )
            }
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

        Text(
            text = "Incoming Appointments",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(color = Color(android.graphics.Color.parseColor("#FF8CF6"))),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { navController.navigate("pubAppointmentPage") },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Paul Chua",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .padding(end = 200.dp)
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
                        .padding(start = 200.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#F0F0F0")))
                    .clickable { navController.navigate("pubAppointmentPage") },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Shannen Lee",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .padding(end = 175.dp)
                )
                val context = LocalContext.current
                Image(
                    painter = painterResource(id = R.drawable.medicine),
                    contentDescription = "Device Information",
                    modifier = Modifier
                        .clickable {
                            val deviceInfo = getDeviceInfo(context)
                            storeInfoToFirestore(deviceInfo)
                        }
                        .height(30.dp)
                        .clip(MaterialTheme.shapes.small)
                        .padding(start = 200.dp)
                )
            }
        }

    }

}