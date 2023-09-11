package com.ttd.dasasahitya.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ttd.dasasahitya.MainActivity
import com.ttd.dasasahitya.R
import com.ttd.dasasahitya.databinding.FragmentCalendarBinding
import com.ttd.dasasahitya.databinding.FragmentNityaKarmaBinding

/**
 * A Calendar Fragment subclass.
 */
class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding

    /**
     * onCreateView function: calls that bindingInflater function type to inflate the view binding,
     * holds the value in the binding property,
     * and returns the root View to satisfy the onCreateView() contract
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }


    /**
     * onViewCreated function: handles the buttons in the fragment
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
        }
    }
}