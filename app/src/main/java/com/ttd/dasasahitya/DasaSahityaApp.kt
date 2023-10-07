package com.ttd.dasasahitya

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp

class DasaSahityaApp:Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: DasaSahityaApp? = null
        lateinit var applicationContext: Context

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        DasaSahityaApp.applicationContext = applicationContext()
    }
}