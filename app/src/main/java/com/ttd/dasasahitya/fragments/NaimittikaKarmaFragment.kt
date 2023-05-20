package com.ttd.dasasahitya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ttd.dasasahitya.DasaSahityaApp
import com.ttd.dasasahitya.MainActivity
import com.ttd.dasasahitya.R
import com.ttd.dasasahitya.databinding.FragmentNaimittikaKarmaBinding
import com.ttd.dasasahitya.utils.EncryptedSharedPreferenceUtil

class NaimittikaKarmaFragment:Fragment() {
    private lateinit var binding: FragmentNaimittikaKarmaBinding

    private fun changeLanguage(binding: FragmentNaimittikaKarmaBinding) {
        if (!EncryptedSharedPreferenceUtil.getSPBoolean(EncryptedSharedPreferenceUtil.isEnglish.value, DasaSahityaApp.applicationContext)) {
            binding.ttdHeader.text = getString(R.string.naimittika_karma)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender_kan)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana_kan)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada_kan)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more_kan)
            binding.txtvrata.text = getString(R.string.vratha)
        } else {
            binding.ttdHeader.text = getString(R.string.naimittika_karma_eng)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more)
            binding.txtvrata.text = getString(R.string.vratha_eng)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNaimittikaKarmaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        changeLanguage(binding)
        binding.backButton.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
        }
        binding.nkVratha.setOnClickListener{
            (activity as MainActivity).basicAlert()
        }
    }
}