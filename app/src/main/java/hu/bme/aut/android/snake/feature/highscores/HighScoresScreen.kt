package hu.bme.aut.android.snake.feature.highscores

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.snake.R
import hu.bme.aut.android.snake.model.SnakeViewModel

@Composable
fun HighScoresScreen(
    snakeViewModel: SnakeViewModel
) {

    val highScores by snakeViewModel.topScores.collectAsStateWithLifecycle(listOf())

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Gray),
    ) {
        Row(
            Modifier
                .background(Color.Gray)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TableText("Name", Modifier.weight(2f))
            TableText("Score", Modifier.weight(1f))
            TableText("Difficulty", Modifier.weight(2f))
        }
        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {
            items(highScores.size) { index ->
                Row(
                    Modifier
                        .background(Color.Gray)
                        .border(1.dp, Color.Black)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TableText(highScores[index].name, Modifier.weight(2f))
                    TableText(highScores[index].score.toString(), Modifier.weight(1f))
                    TableText(highScores[index].difficulty, Modifier.weight(2f))
                }
            }
        }
        Text(text = "High Scores Screen")
    }
}

@Composable
fun TableText(text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal)),
            modifier = Modifier.padding(8.dp)
        )
    }
}