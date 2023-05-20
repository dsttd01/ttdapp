package com.ttd.dasasahitya

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
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
/*
    // todo: function to enable system back button to function properly
    override fun onBackPressed() {
        super.getOnBackPressedDispatcher().onBackPressed()
    }*/

    private val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(
            applicationContext,
            android.R.string.no, Toast.LENGTH_SHORT
        ).show()
    }

    // creates an alert dialogue box when called
    fun basicAlert() {

        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setTitle("Feature unavailable!")
            setMessage("Under development")
            setPositiveButton(
                "OK",
                DialogInterface.OnClickListener(
                    function = positiveButtonClick
                )
            )
            show()
        }

    }
}