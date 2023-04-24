package com.ttd.dasasahitya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ttd.dasasahitya.MainActivity
import com.ttd.dasasahitya.R
import com.ttd.dasasahitya.databinding.FragmentNaimittikaKarmaBinding

class NaimittikaKarmaFragment:Fragment() {
    private lateinit var binding: FragmentNaimittikaKarmaBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNaimittikaKarmaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
        }
    }
}