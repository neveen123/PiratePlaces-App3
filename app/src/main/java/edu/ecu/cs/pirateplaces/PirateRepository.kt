package edu.ecu.cs.pirateplaces

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import edu.ecu.cs.pirateplaces.database.PirateDatabase
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "places-database"
class PirateRepository private constructor(context: Context){

    private val database : PirateDatabase = Room.databaseBuilder(
        context.applicationContext,
        PirateDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val pirateDao = database.pirateDao()
    private val executor =  Executors.newSingleThreadExecutor()

    fun getPirates(): LiveData<List<PiratePlace>> = pirateDao.getPirates()

    fun getPirate(id: UUID): LiveData<PiratePlace?> = pirateDao.getPirate(id)

    fun updatePiratePlaces(place: PiratePlace){
        executor.execute{pirateDao.updatePiratePlaces(place)}
    }

    fun addPiratePlace(place: PiratePlace){
        executor.execute{pirateDao.addPiratePlace(place)}
    }

    companion object {
        private var INSTANCE: PirateRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = PirateRepository(context)
            }
        }

        fun get(): PirateRepository {
            return INSTANCE ?: throw IllegalStateException("PirateRepository must be initialized")
        }
    }
}
