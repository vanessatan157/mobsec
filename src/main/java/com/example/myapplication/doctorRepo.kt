package com.example.myapplication

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

object DoctorRepo {
    private val TAG = "DoctorRepo"
    private val db = FirebaseFirestore.getInstance()

    fun fetchDoctorList(onResult: (List<String>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("doctorlist").get()
            .addOnSuccessListener { documents ->
                Log.d(TAG, "Successfully fetched ${documents.size()} documents")
                val doctorNames = documents.mapNotNull { it.getString("Name") }
                onResult(doctorNames)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
                onFailure(exception)
            }
    }

    fun addDoctorToFirestore(doctorName: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val doctor = hashMapOf("Name" to doctorName)

        // Add a new document with a generated ID to the 'doctorlist' collection
        db.collection("doctorlist")
            .add(doctor)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                onFailure(e)
            }
    }
}
