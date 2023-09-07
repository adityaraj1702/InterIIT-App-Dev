package com.adityastudios.interiitappdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adityastudios.interiitappdev.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}