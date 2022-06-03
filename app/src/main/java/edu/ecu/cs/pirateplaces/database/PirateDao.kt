package edu.ecu.cs.pirateplaces.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import edu.ecu.cs.pirateplaces.PiratePlace
import java.util.*

@Dao
interface PirateDao {

    @Query("SELECT * FROM pirateplace")
   // fun getPirates(): List<PiratePlace>
    fun getPirates(): LiveData<List<PiratePlace>>

    @Query("SELECT * FROM pirateplace WHERE id=(:id)")
    //fun getPirate(id: UUID): PiratePlace?
    fun getPirate(id: UUID): LiveData<PiratePlace?>
}