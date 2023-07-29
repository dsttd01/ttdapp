package com.ttd.dasasahitya.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UdayaVaniService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var storageReference: StorageReference
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.setOnPreparedListener{
            it.start()
        }
        mediaPlayer.prepare()
        return START_NOT_STICKY

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        mediaPlayer = MediaPlayer()
        storageReference = FirebaseStorage.getInstance("gs://dasa-sahithya-project.appspot.com").reference
//        val listRef = storageReference.child("Udayavaani")
//        listRef.listAll()
//            .addOnSuccessListener {listRes ->
//                listRes?.let {
//                    it.items.iterator().forEach { ref->
//                        Log.w("TAG", "onCreate: ${ref?.listAll()}")
//                    }
//                }
//            }
        val imageUrls = mutableListOf<String>()
        CoroutineScope(Dispatchers.IO).launch {
            val images = storageReference.child("Udayavaani").listAll().await()

            for(image in images.items) {
                val url = image.downloadUrl.await()
                Log.w("TAG", "onCreate: $url")
                imageUrls.add(url.toString())
            }
        }
        mediaPlayer.setDataSource(imageUrls[0])
//        mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/dasa-sahithya-project.appspot.com/o/Pravachana%2FBhagavata%202.mp3?alt=media&token=37774c68-887f-4666-be0c-735866bf0a64")
        mediaPlayer.isLooping = true
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}