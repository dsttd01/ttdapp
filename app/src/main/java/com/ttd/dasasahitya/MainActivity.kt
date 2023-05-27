package com.ttd.dasasahitya

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
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

    //@todo: add this code block once menu is finalized

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dotted_more_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.profile -> {
                Toast.makeText(this,"Profile",Toast.LENGTH_LONG).show()
                true
            }
            R.id.events -> {
                Toast.makeText(this,"Event",Toast.LENGTH_LONG).show()
                true
            }
            R.id.gallery -> {
                Toast.makeText(this,"Gallery",Toast.LENGTH_LONG).show()
                true
            }
            R.id.website -> {
                Toast.makeText(this,"Website",Toast.LENGTH_LONG).show()
                true
            }
            R.id.about -> {
                Toast.makeText(this,"About",Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}