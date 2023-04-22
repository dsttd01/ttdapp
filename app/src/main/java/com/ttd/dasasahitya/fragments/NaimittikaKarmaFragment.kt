package com.ttd.dasasahitya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
}