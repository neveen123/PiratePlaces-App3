package edu.ecu.cs.pirateplaces

import android.app.Application

class PiratePlacesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PirateRepository.initialize(this)
    }
}