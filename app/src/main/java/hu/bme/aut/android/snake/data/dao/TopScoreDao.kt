package hu.bme.aut.android.snake.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hu.bme.aut.android.snake.data.Entity.TopScore
import kotlinx.coroutines.flow.Flow

@Dao
interface TopScoreDao {
    @Query("SELECT * FROM topscore ORDER BY score DESC LIMIT 10")
    fun getAll(): Flow<List<TopScore>>

    @Insert
    suspend fun insert(topScore: TopScore)

    @Update
    suspend fun update(topScore: TopScore)
}