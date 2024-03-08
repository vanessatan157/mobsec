
package com.example.myapplication
/*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import com.example.myapplication.LoginScreen



class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Your existing login button click listener
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login successful
                    val user = auth.currentUser
                    retrieveUserProfile(user?.uid)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun retrieveUserProfile(userId: String?) {
        userId?.let {
            val userDocument = firestore.collection("users").document(it)

            userDocument.get().addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // User profile exists
                    val username = document.getString("username")
                    val email = document.getString("email")

                    // Proceed to the authenticated section of your app
                    // For example, start a new activity or fragment
                    startActivity(Intent(this, AuthenticatedActivity::class.java))
                    finish()
                } else {
                    // User profile doesn't exist
                    Toast.makeText(baseContext, "User profile not found.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
*/
