package com.ttd.dasasahitya.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class AcharyaVaniService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var storageReference: StorageReference
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.setOnPreparedListener{
            it.start()
        }
        return START_NOT_STICKY

    }

    override fun onCreate() {
        mediaPlayer = MediaPlayer()
        storageReference = FirebaseStorage.getInstance("gs://dasa-sahithya-project.appspot.com").reference
        val imageUrls = mutableListOf<String>()
        CoroutineScope(Dispatchers.IO).launch {
            FirebaseAuth.getInstance().signInAnonymously().await()
            val images = storageReference.child("Pravachana").listAll().await()
            for(image in images.items) {
                val url = image.downloadUrl.await()
                Log.w("TAG", "onCreate: $url")
                imageUrls.add(url.toString())
            }
            CoroutineScope(Dispatchers.Main).launch {
                mediaPlayer.setDataSource(imageUrls[Random.nextInt(0,imageUrls.size-1)])
                mediaPlayer.prepare()
            }

        }
        mediaPlayer.isLooping = true
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}