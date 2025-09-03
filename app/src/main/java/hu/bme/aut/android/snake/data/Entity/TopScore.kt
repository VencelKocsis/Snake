package hu.bme.aut.android.snake.data.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topscore")
data class TopScore(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "difficulty") var difficulty: String,
    @ColumnInfo(name = "score") var score: Int
)