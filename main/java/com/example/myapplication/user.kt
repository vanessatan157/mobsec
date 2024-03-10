package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class User {
    var Name: String? = null
    var Email: String? = null
    var UID: String? = null

    constructor(){}

    constructor(Name: String?, Email: String?, UID: String?){
        this.Name = Name
        this.Email = Email
        this.UID = UID
    }
}