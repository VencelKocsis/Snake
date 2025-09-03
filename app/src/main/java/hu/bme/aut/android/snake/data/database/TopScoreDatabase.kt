package hu.bme.aut.android.snake.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.bme.aut.android.snake.data.Entity.TopScore
import hu.bme.aut.android.snake.data.dao.TopScoreDao

@Database(entities = [TopScore::class], version = 1)
abstract class TopScoreDatabase : RoomDatabase() {
    abstract fun topScoresDao(): TopScoreDao

    companion object {
        @Volatile
        private var INSTANCE: TopScoreDatabase? = null

        fun getDatabase(context: Context): TopScoreDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TopScoreDatabase::class.java,
                    "topscore_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}