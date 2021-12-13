package com.example.adventofcode2021

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class Puzzle12Activity : AppCompatActivity() {
    var launched = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!launched) {
            launched = true
            GlobalScope.launch {
                processFish()
            }
        }
    }

    suspend fun processFish() {
        var file = File("/sdcard/puzzle6/input.txt")
        var lines = file.readLines()

        val fish = lines[0].split(",").map { it.toInt() }.toMutableList()
        val fishDao = FishDatabase.getDatabase(this).getDataDao()
        fishDao.deleteAllFish()
        for (i in 0 until fish.size) {
            fishDao.insertFish(Fish(i, fish[i]))
        }
        println("starting number of fish: ${fishDao.numFish()}")

        for (days in 0 until 256) {
            var fishToCreate = 0
            for (i in 0 until fishDao.numFish()) {
                var nextFish = fishDao.findFish(i)
                nextFish?.let { aFish ->
                    if (aFish.daysLeft > 0) {
                        aFish.daysLeft--
                    } else {
                        aFish.daysLeft = 6
                        fishToCreate++
                    }
                    fishDao.updateFish(aFish)
                }
            }
            for (i in 0 .. fishToCreate) {
                val nextIndex = fishDao.numFish()
                fishDao.insertFish(Fish(i + nextIndex, 8))
            }
            println("number of fish after day $days: ${fishDao.numFish()}")
        }

        println("number of fish: ${fishDao.numFish()}")
    }
}

@Dao
interface FishDao {
    @Insert
    suspend fun insertFish(data: Fish)

    @Update
    suspend fun updateFish(data: Fish)

    @Query("DELETE FROM Fish")
    suspend fun deleteAllFish()

    @Query("SELECT * FROM Fish WHERE fishNumber=:fishNumber")
    suspend fun findFish(fishNumber: Int) : Fish?

    @Query("SELECT COUNT(fishNumber) FROM Fish")
    suspend fun numFish() : Int
}

@Entity
data class Fish (
    @PrimaryKey(autoGenerate = false) val fishNumber: Int,
    var daysLeft: Int
)

@Database(
    entities = [Fish::class],
    version = 1
)
abstract class FishDatabase : RoomDatabase() {
    abstract fun getDataDao(): FishDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        var INSTANCE: FishDatabase? = null

        fun getDatabase(context: Context): FishDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FishDatabase::class.java,
                    "fish_database"

                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
