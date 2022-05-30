package edu.ecu.cs.pirateplaces

import androidx.lifecycle.ViewModel

class PiratePlacesListViewModel: ViewModel() {
    private val sampleNames = listOf(
        "Bill", "James", "Edward", "Mary", "Alice", "Susan", "Joe", "Beth"
    )

    val piratePlaceList = List(100) {
        PiratePlace(name="Place $it", visitedWith= sampleNames.shuffled().take(it%3+1).joinToString(", "))
    }
}