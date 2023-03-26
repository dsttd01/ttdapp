package com.ttd.dasasahitya.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class AcharyaVaniService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
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

    override fun onCreate() {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/dasa-sahithya-project.appspot.com/o/Pravachana%2FBhagavata%204.mp3?alt=media&token=7c1d50dd-6619-43c1-83fc-6cd8b47d7acb")
        mediaPlayer.isLooping = true
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}