package com.ttd.dasasahitya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttd.dasasahitya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(2).isEnabled = false

    }
}