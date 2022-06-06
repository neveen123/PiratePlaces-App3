package edu.ecu.cs.pirateplaces

import androidx.lifecycle.ViewModel

class PiratePlacesListViewModel: ViewModel() {

    private val pirateRepository = PirateRepository.get()
    val pirateListLiveData = pirateRepository.getPirates()

    fun addPiratePlace(place: PiratePlace) {
        pirateRepository.addPiratePlace(place)
    }
}