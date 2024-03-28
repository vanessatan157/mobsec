package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map


data class DoctorList(
    val id: String = "",
    val name: String = ""
)

open class DoctorListViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow<String?>(null)
    val isError: StateFlow<String?> = _isError.asStateFlow()

    private val _doctorList = MutableStateFlow<List<DoctorList>>(emptyList())
    val doctorList: StateFlow<List<DoctorList>> = _doctorList.asStateFlow()

    open val doctorNames: Flow<List<String>> = _doctorList.map { it.map { doctor -> doctor.name } }


    init {
        fetchDoctors()
    }

    companion object {
        private const val TAG = "DoctorListViewModel"
    }


    private fun fetchDoctors() = viewModelScope.launch {
        _isLoading.value = true
        DoctorRepo.fetchDoctorList(
            onResult = { names ->
                Log.d(TAG, "Doctors fetched: $names")
                // Rest of the success handling code
            },
            onFailure = { exception ->
                Log.e(TAG, "Failed to fetch doctors", exception)
                // Rest of the failure handling code
            }
        )
    }


    fun addDoctor(doctorName: String) = viewModelScope.launch {
        DoctorRepo.addDoctorToFirestore(
            doctorName = doctorName,
            onSuccess = { /* Handle success */ },
            onFailure = { exception ->
                Log.e(TAG, "Failed to add doctor", exception)
                _isError.value = exception.toString()
            }
        )
    }
}
