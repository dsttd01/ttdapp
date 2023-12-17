package com.ttd.dasasahitya.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.ttd.dasasahitya.GetSongs
import com.ttd.dasasahitya.MainActivity
import com.ttd.dasasahitya.R
import com.ttd.dasasahitya.adapters.AudioFileAdapter
import com.ttd.dasasahitya.databinding.FragmentDasarapadaBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DasarapadaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DasarapadaFragment : Fragment(R.layout.fragment_dasarapada) {
    private lateinit var binding: FragmentDasarapadaBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AudioFileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDasarapadaBinding.inflate(inflater, container, false)

        // Initialize RecyclerView and set layout manager
        recyclerView = binding.DSPRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val audioFiles: List<String> = retrieveFilesFromFirebase()

        // Initialize your adapter and set it to the RecyclerView
        adapter = AudioFileAdapter(audioFiles)
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
        }
    }

    private fun retrieveFilesFromFirebase(): List<String> {

        // An empty list to hold the file names
        val audioFileModelList = mutableListOf<GetSongs>()
        val audioFileList = mutableListOf<String>()

        // Get a reference to the Firebase Storage instance
        val storage: FirebaseStorage = FirebaseStorage.getInstance()

        // Get a reference to the Firebase Storage location where the files are stored
        val storageRef: StorageReference =
            storage.reference.child("DaasarapadagaluAudio")

        // List all the files in the Firebase Storage location
        storageRef.listAll()
            .addOnSuccessListener { result ->
                // Add the names of the files to the list
                for (item in result.items){
                    Log.d("File", item.name)
                    audioFileList.add(item.name)
                    audioFileModelList.add(GetSongs(item.name))
            }
        }
        .addOnFailureListener { exception ->
            // Handle any errors that may occur
            Log.e("Storage", "Error listing files", exception)
        }

        return audioFileList
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
}