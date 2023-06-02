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
import com.ttd.dasasahitya.databinding.FragmentNityaKarmaBinding
import com.ttd.dasasahitya.utils.EncryptedSharedPreferenceUtil

class NithyaKarmaFragment : Fragment() {
    private lateinit var binding: FragmentNityaKarmaBinding

    private fun changeLanguage(binding: FragmentNityaKarmaBinding) {
        if (!EncryptedSharedPreferenceUtil.getSPBoolean(EncryptedSharedPreferenceUtil.isEnglish.value, DasaSahityaApp.applicationContext)) {
            binding.ttdHeader.text = getString(R.string.nitya_krma)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender_kan)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana_kan)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada_kan)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more_kan)
            binding.txtpratahsmarana.text = getString(R.string.pratah_smarana)
            binding.txtSuprabhata.text = getString(R.string.suprabhata)
            binding.txtSnana.text = getString(R.string.snana)
            binding.txtPooja.text = getString(R.string.pooja)
            binding.txtDhyana.text = getString(R.string.dhyana)
            binding.txtAnusandhana.text = getString(R.string.anusandhana)
            binding.txtChintana.text = getString(R.string.chintana)
        } else {
            binding.ttdHeader.text = getString(R.string.nitya_karma_eng)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more)
            binding.txtpratahsmarana.text = getString(R.string.pratah_smarana_eng)
            binding.txtSuprabhata.text = getString(R.string.suprabhata_eng)
            binding.txtSnana.text = getString(R.string.snana_eng)
            binding.txtPooja.text = getString(R.string.pooja_eng)
            binding.txtDhyana.text = getString(R.string.dhyana_eng)
            binding.txtAnusandhana.text = getString(R.string.anusandhana_eng)
            binding.txtChintana.text = getString(R.string.chintana_eng)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNityaKarmaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        changeLanguage(binding)
        binding.backButton.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
        }
        binding.nitkPs.setOnClickListener{
            getAlertBox(it)
        }
        binding.nitkSup.setOnClickListener{
            getAlertBox(it)
        }
        binding.nitkSnana.setOnClickListener{
            getAlertBox(it)
        }
        binding.nitkPooja.setOnClickListener{
            getAlertBox(it)
        }
        binding.nitkDhyana.setOnClickListener{
            getAlertBox(it)
        }
        binding.nitkAnusandhana.setOnClickListener{
            getAlertBox(it)
        }
        binding.nitkChintana.setOnClickListener{
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