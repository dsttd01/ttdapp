package com.ttd.dasasahitya

import android.app.Application
import android.content.Context

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
        DasaSahityaApp.applicationContext = applicationContext()
    }
}