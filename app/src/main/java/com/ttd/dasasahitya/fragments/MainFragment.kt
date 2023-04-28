package com.ttd.dasasahitya.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ttd.dasasahitya.DasaSahityaApp
import com.ttd.dasasahitya.MainActivity
import com.ttd.dasasahitya.R
import com.ttd.dasasahitya.adapters.ImageViewAdapter
import com.ttd.dasasahitya.databinding.FragmentMainBinding
import com.ttd.dasasahitya.service.AcharyaVaniService
import com.ttd.dasasahitya.service.UdayaVaniService
import com.ttd.dasasahitya.utils.EncryptedSharedPreferenceUtil
import kotlin.math.abs

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(2).isEnabled = false

        return binding.root
    }

    private val runnable = Runnable {
        binding.viewPager.currentItem = +1
    }

    private fun changeLanguage(binding: FragmentMainBinding) {
        if (!EncryptedSharedPreferenceUtil.getSPBoolean(EncryptedSharedPreferenceUtil.isEnglish.value, DasaSahityaApp.applicationContext)) {
            binding.language.setBackgroundResource(R.drawable.kannada)
            binding.ttdHs.text = getString(R.string.hare_shrinivasa_kan)
            binding.ttdHeader.text = getString(R.string.ttd_dasa_sahitya)
            binding.txtAcharyavani.text = getString(R.string.acharya_vani)
            binding.txtUdayavaani.text = getString(R.string.udaya_vani)
            binding.txtPaatha.text = getString(R.string.pattha)
            binding.txtParaayana.text = getString(R.string.parayana)
            binding.txtnityakarma.text = getString(R.string.nitya_krma)
            binding.txtnimittika.text = getString(R.string.naimittika_karma)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender_kan)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana_kan)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada_kan)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more_kan)
        } else {
            binding.language.setBackgroundResource(R.drawable.english)
            binding.ttdHs.text = getString(R.string.hare_shrinivasa)
            binding.ttdHeader.text = getString(R.string.ttd_dasa_sahitya_eng)
            binding.txtAcharyavani.text = getString(R.string.acharya_vani_eng)
            binding.txtUdayavaani.text = getString(R.string.udaya_vani_eng)
            binding.txtPaatha.text = getString(R.string.pattha_eng)
            binding.txtParaayana.text = getString(R.string.parayana_eng)
            binding.txtnityakarma.text = getString(R.string.nitya_karma_eng)
            binding.txtnimittika.text = getString(R.string.naimittika_karma_eng)
            binding.bottomNavView.menu.getItem(0).title = getString(R.string.calender)
            binding.bottomNavView.menu.getItem(1).title = getString(R.string.pravachana)
            binding.bottomNavView.menu.getItem(3).title = getString(R.string.dasara_pada)
            binding.bottomNavView.menu.getItem(4).title = getString(R.string.more)
        }

    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        binding.viewPager.setPageTransformer(transformer)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        changeLanguage(binding)
        handler = Handler(Looper.myLooper()!!)
        binding.language.setOnClickListener {
            EncryptedSharedPreferenceUtil.saveSPBoolean(EncryptedSharedPreferenceUtil.isEnglish.value,
                !EncryptedSharedPreferenceUtil.getSPBoolean(EncryptedSharedPreferenceUtil.isEnglish.value, (activity as MainActivity)),
                (activity as MainActivity))
            changeLanguage(binding)
        }
        binding.viewPager.adapter = ImageViewAdapter(binding.viewPager)
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        setUpTransformer()


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
        binding.uvIvPlay.setOnClickListener {
            (activity as MainActivity).startService(
                Intent(
                    (activity as MainActivity),
                    UdayaVaniService::class.java
                )
            )
            binding.uvIvPlay.visibility = View.GONE
            binding.uvIvPause.visibility = View.VISIBLE
            (activity as MainActivity).stopService(
                Intent(
                    (activity as MainActivity),
                    AcharyaVaniService::class.java
                )
            )
            binding.avIvPlay.visibility = View.VISIBLE
            binding.avIvPause.visibility = View.GONE
        }
        binding.uvIvPause.setOnClickListener {
            (activity as MainActivity).stopService(
                Intent(
                    (activity as MainActivity),
                    UdayaVaniService::class.java
                )
            )
            binding.uvIvPlay.visibility = View.VISIBLE
            binding.uvIvPause.visibility = View.GONE
        }

        binding.avIvPlay.setOnClickListener {
            (activity as MainActivity).startService(
                Intent(
                    (activity as MainActivity),
                    AcharyaVaniService::class.java
                )
            )
            binding.avIvPlay.visibility = View.GONE
            binding.avIvPause.visibility = View.VISIBLE
            (activity as MainActivity).stopService(
                Intent(
                    (activity as MainActivity),
                    UdayaVaniService::class.java
                )
            )
            binding.uvIvPlay.visibility = View.VISIBLE
            binding.uvIvPause.visibility = View.GONE
        }
        binding.avIvPause.setOnClickListener {
            (activity as MainActivity).stopService(
                Intent(
                    (activity as MainActivity),
                    AcharyaVaniService::class.java
                )
            )
            binding.avIvPlay.visibility = View.VISIBLE
            binding.avIvPause.visibility = View.GONE
        }

        binding.cardPaatha.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, PaathaFragment()).addToBackStack(null).commit()
        }
        binding.cardNk.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, NithyaKarmaFragment()).addToBackStack(null).commit()
        }
        binding.cardNaik.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, NaimittikaKarmaFragment()).addToBackStack(null).commit()
        }
    }
}