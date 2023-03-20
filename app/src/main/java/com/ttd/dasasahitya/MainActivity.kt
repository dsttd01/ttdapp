package com.ttd.dasasahitya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttd.dasasahitya.databinding.ActivityMainBinding
import com.ttd.dasasahitya.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, MainFragment()).commit()
    }
}