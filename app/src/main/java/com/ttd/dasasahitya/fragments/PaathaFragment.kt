package com.ttd.dasasahitya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ttd.dasasahitya.MainActivity
import com.ttd.dasasahitya.R
import com.ttd.dasasahitya.databinding.FragmentPaathaBinding

class PaathaFragment : Fragment() {
    private lateinit var binding: FragmentPaathaBinding
    private var isEnglish = true

    private fun changeLanguage(binding: FragmentPaathaBinding) {
        if (!isEnglish) {
            binding.switchLanguage.setBackgroundResource(R.drawable.kannada)
            binding.ttdHeader.text = getString(R.string.ttd_dasa_sahitya)
            binding.txtpatha.text = getString(R.string.pattha)
            binding.txtPaathaMen.text = getString(R.string.paatha_for_men)
            binding.txtPaathaWomen.text = getString(R.string.paatha_for_women)
            binding.txtPaathaKids.text = getString(R.string.paatha_for_kids)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender_kan)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana_kan)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada_kan)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more_kan)
        } else {
            binding.switchLanguage.setBackgroundResource(R.drawable.english)
            binding.ttdHeader.text = getString(R.string.ttd_dasa_sahitya_eng)
            binding.txtPaathaMen.text = getString(R.string.paatha_for_men_eng)
            binding.txtpatha.text = getString(R.string.pattha_eng)
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
        binding.backButton.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
        }
        binding.switchLanguage.setOnClickListener{
            isEnglish = !isEnglish
            changeLanguage(binding)
        }
    }
}