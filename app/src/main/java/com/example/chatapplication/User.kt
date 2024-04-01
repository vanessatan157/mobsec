package com.example.chatapplication

class User {
    var email: String? = null
    var name: String? = null
    var uid: String? = null

    constructor()

    constructor(email: String, name: String?, uid: String?) {
        this.email = name
        this.name = email
        this.uid = uid
    }
}