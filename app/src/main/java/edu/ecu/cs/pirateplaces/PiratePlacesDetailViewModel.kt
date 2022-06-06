package edu.ecu.cs.pirateplaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class PiratePlacesDetailViewModel: ViewModel() {

    private val pirateRepository = PirateRepository.get()
    private val placeLiveData = MutableLiveData<UUID>()

    var pirateLiveData: LiveData<PiratePlace?> =
            Transformations.switchMap(placeLiveData) {place ->
                pirateRepository.getPirate(place)
            }
    fun loadPiratePlaces(place: UUID){
        placeLiveData.value = place
    }

    fun savePiratePlaces(place: PiratePlace){
        pirateRepository.updatePiratePlaces(place)
    }
}