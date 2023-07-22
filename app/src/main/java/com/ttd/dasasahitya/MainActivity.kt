package com.ttd.dasasahitya

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ttd.dasasahitya.databinding.ActivityMainBinding
import com.ttd.dasasahitya.fragments.MainFragment
import com.ttd.dasasahitya.utils.EncryptedSharedPreferenceUtil
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EncryptedSharedPreferenceUtil.saveSPBoolean(
            EncryptedSharedPreferenceUtil.isEnglish.value,
            true,
            DasaSahityaApp.applicationContext
        )
        supportFragmentManager.beginTransaction().replace(R.id.fragment, MainFragment()).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.fragment) !is MainFragment) {
            super.onBackPressed()
        } else {
            MaterialAlertDialogBuilder(this)
                .setMessage("Are your sure that you want to exit?")
                .setPositiveButton("OK") { _, _ ->
                    startActivity(
                        Intent(Intent.ACTION_MAIN)
                            .addCategory(Intent.CATEGORY_HOME)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
    }
}