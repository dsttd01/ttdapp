package com.ttd.dasasahitya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttd.dasasahitya.databinding.ActivityMainBinding
import com.ttd.dasasahitya.fragments.MainFragment
import com.ttd.dasasahitya.utils.EncryptedSharedPreferenceUtil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EncryptedSharedPreferenceUtil.saveSPBoolean(EncryptedSharedPreferenceUtil.isEnglish.value, true, DasaSahityaApp.applicationContext)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, MainFragment()).commit()
    }
}