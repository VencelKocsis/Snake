package hu.bme.aut.android.snake.feature.highscores

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import hu.bme.aut.android.snake.data.Entity.TopScore
import hu.bme.aut.android.snake.model.SnakeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HighScoresScreen(
    snakeViewModel: SnakeViewModel
) {

    val highScores by snakeViewModel.topScores.collectAsStateWithLifecycle(listOf())
    var showDialog by remember { mutableStateOf(false) }
    var selectedScore by remember { mutableStateOf<TopScore?>(null) }

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
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = {},
                            onLongClick = {
                                selectedScore = highScores[index]
                                showDialog = true
                            }
                        ),
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

    if (showDialog && selectedScore != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Delete score?") },
            confirmButton = {
                TextButton(onClick = {
                    snakeViewModel.deleteHighScore(selectedScore!!) // delete only here
                    showDialog = false
                    selectedScore = null
                }) { Text("Delete") }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    selectedScore = null
                }) { Text("Cancel") }
            }
        )
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