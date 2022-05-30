package edu.ecu.cs.pirateplaces

import java.io.Serializable
import java.util.*

data class PiratePlace(
    val id: UUID = UUID.randomUUID(),
    var name: String = "",
    var visitedWith: String = "",
    var lastVisited: Date = Date()) : Serializable