package hu.bme.aut.android.snake.feature.startgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import hu.bme.aut.android.snake.R

@Composable
fun StartGameScreen (
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var playerName by remember { mutableStateOf("") }
    var difficulty by remember { mutableStateOf(GameDifficulty.EASY) }

    var emptyField by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //GameModeMap
        val gameModeMap = mapOf(
            0.0 to Triple(GameDifficulty.EASY, 0.0, Color.Green),
            0.5 to Triple(GameDifficulty.MEDIUM, 0.5, Color.Yellow),
            1.0 to Triple(GameDifficulty.HARD, 1.0, Color.Red)
        )

        //Difficulty Label
        Text(
            text = difficulty.toString(),
            color = gameModeMap.values.first { it.first == difficulty }.third,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal))
        )

        //Difficulty Slider
        Slider(
            value = gameModeMap.values.first { it.first == difficulty }.second.toFloat(),
            onValueChange = {
                difficulty = gameModeMap[it.toDouble()]!!.first
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.Black,
                activeTrackColor = Color.Black,
                inactiveTrackColor = Color.Black,
            ),
            steps = 1,
            valueRange = 0f..1.0f
        )

        //Name Field
        OutlinedTextField(
            value = playerName,
            onValueChange = {
                playerName = it
            },
            isError = emptyField,
            supportingText = { if (emptyField) Text(
                text = "Please enter your name",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal))
            ) },
            label = { Text(
                "Player Name",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal))
            ) },
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal)),
                fontSize = 30.sp
            )
        )

        //Start Button
        Button(
            onClick = {
                if (playerName.isNotEmpty()) {
                    navController.navigate("gameScreen/${playerName}/${difficulty.name}")
                } else {
                    emptyField = true
                }
            }
        ) {
            Text(
                text = "Start Game",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal)),
            )
        }
    }
}

enum class GameDifficulty() {
    EASY,
    MEDIUM,
    HARD
}