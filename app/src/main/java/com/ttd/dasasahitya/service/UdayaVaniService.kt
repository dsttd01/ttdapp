package com.ttd.dasasahitya.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class UdayaVaniService : Service() {
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
        mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/dasa-sahithya-project.appspot.com/o/Pravachana%2FBhagavata%202.mp3?alt=media&token=37774c68-887f-4666-be0c-735866bf0a64")
        mediaPlayer.isLooping = true
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}