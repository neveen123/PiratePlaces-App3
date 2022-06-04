package edu.ecu.cs.pirateplaces.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.ecu.cs.pirateplaces.PiratePlace

@Database(entities = [ PiratePlace::class ], version=1, exportSchema = false)
@TypeConverters(PirateTypeConverters::class)
abstract class PirateDatabase : RoomDatabase() {
    abstract fun pirateDao(): PirateDao
}