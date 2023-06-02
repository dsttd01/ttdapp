package com.ttd.dasasahitya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.ttd.dasasahitya.DasaSahityaApp
import com.ttd.dasasahitya.MainActivity
import com.ttd.dasasahitya.R
import com.ttd.dasasahitya.databinding.FragmentPaathaBinding
import com.ttd.dasasahitya.utils.EncryptedSharedPreferenceUtil

class PaathaFragment : Fragment() {
    private lateinit var binding: FragmentPaathaBinding

    private fun changeLanguage(binding: FragmentPaathaBinding) {
        if (!EncryptedSharedPreferenceUtil.getSPBoolean(EncryptedSharedPreferenceUtil.isEnglish.value, DasaSahityaApp.applicationContext)) {
            binding.ttdHeader.text = getString(R.string.pattha)
            binding.txtPaathaMen.text = getString(R.string.paatha_for_men)
            binding.txtPaathaWomen.text = getString(R.string.paatha_for_women)
            binding.txtPaathaKids.text = getString(R.string.paatha_for_kids)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender_kan)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana_kan)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada_kan)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more_kan)
        } else {
            binding.ttdHeader.text = getString(R.string.pattha_eng)
            binding.txtPaathaMen.text = getString(R.string.paatha_for_men_eng)
            binding.txtPaathaWomen.text = getString(R.string.paatha_for_women_eng)
            binding.txtPaathaKids.text = getString(R.string.paatha_for_kids_eng)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaathaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        changeLanguage(binding)
        binding.backButton.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
        }
        binding.pcvMen.setOnClickListener{
            getAlertBox(it)
        }
        binding.pcvWomen.setOnClickListener{
            getAlertBox(it)
        }
        binding.pcvKids.setOnClickListener{
            getAlertBox(it)
        }
    }

    private fun getAlertBox(v: View) {
        MaterialAlertDialogBuilder(activity as MainActivity)
            .setTitle("Developer Message")
            .setMessage("Will soon be available")
            .setNeutralButton("OK") { _, _ ->
                Snackbar.make(v, "Exited from alert", Snackbar.LENGTH_SHORT).show()
            }
            .show()
    }
}