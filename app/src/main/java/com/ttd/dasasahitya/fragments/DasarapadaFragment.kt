package com.ttd.dasasahitya.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.ttd.dasasahitya.MainActivity
import com.ttd.dasasahitya.R
// import com.ttd.dasasahitya.adapters.AudioFileAdapter
import com.ttd.dasasahitya.databinding.FragmentCalendarBinding
import com.ttd.dasasahitya.databinding.FragmentDasarapadaBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DasarapadaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DasarapadaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentDasarapadaBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDasarapadaBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DasarapadaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DasarapadaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
        }
        fetchAudioFileFromFirebase()
        binding.dasarapadaSwitch.setOnCheckedChangeListener{_,isChecked->
            if (isChecked) {
                // The switch is checked (ON), start playing the audio
                startAudio()
            } else {
                // The switch is unchecked (OFF), pause the audio
                pauseAudio()
            }
        }
    }

    private fun pauseAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    private fun startAudio() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Release MediaPlayer resources when the activity is destroyed
    }

    private fun fetchAudioFileFromFirebase() {
        val storage = FirebaseStorage.getInstance()
        val storageRef: StorageReference = storage.reference
        // For simplicity, we'll use MediaPlayer to play the audio in this example.
        mediaPlayer = MediaPlayer()

        // Replace "audio/file_name.mp3" with the path to your audio file in Firebase Storage
        val audioFolderReference: StorageReference = storageRef.child("DaasarapadagaluAudio/DasaSahithyam_01 NODIDE VENKATARAMANANA_02  JAYAGALU AAGALI.mp3")

        /*audioFolderReference.listAll()
            .addOnSuccessListener { listResult ->
                for (item in listResult.items) {

                }
            }*/

        audioFolderReference.downloadUrl.addOnSuccessListener { uri ->
            // Here, you have the download URL of the audio file.
            // You can now use this URL to play the audio using MediaPlayer or any other library of your choice.
            mediaPlayer.setDataSource(uri.toString())
            mediaPlayer.prepare()
        }.addOnFailureListener {
            // Handle any error that occurs while fetching the audio file.
            // For example, show a Toast message or log the error.
        }
    }
}