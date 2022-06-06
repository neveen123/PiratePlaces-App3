package edu.ecu.cs.pirateplaces.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import edu.ecu.cs.pirateplaces.PiratePlace
import java.util.*

@Dao
interface PirateDao {

    @Query("SELECT * FROM pirateplace")
    fun getPirates(): LiveData<List<PiratePlace>>

    @Query("SELECT * FROM pirateplace WHERE id=(:id)")
    fun getPirate(id: UUID): LiveData<PiratePlace?>

    @Update
    fun updatePiratePlaces(place: PiratePlace)

    @Insert
    fun addPiratePlace(place: PiratePlace)
}