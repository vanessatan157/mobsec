package com.example.userprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userprofile.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Use hardcoded text for testing
        binding.tvUsername.text = "Username: John Doe"
        binding.tvEmail.text = "Email: john.doe@example.com"
    }
}
