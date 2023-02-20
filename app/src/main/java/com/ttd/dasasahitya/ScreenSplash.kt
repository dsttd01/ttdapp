package com.ttd.dasasahitya

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttd.dasasahitya.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.*

class ScreenSplash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activityScope.launch {
            delay(3000)
            val intent = Intent(this@ScreenSplash, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}