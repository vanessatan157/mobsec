package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivityRequest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.requestactivity_main)
    }

    fun ReqChat(view: View) {
        setContentView(R.layout.user_layout)
    }

    fun ReqMedCert(view: View){}

    fun ReqMed(view: View){}

    fun ModAppt(view: View){}

    fun BookAppt(view: View){}

}