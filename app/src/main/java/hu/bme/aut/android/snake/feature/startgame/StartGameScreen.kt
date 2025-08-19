package hu.bme.aut.android.snake.feature.startgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun StartGameScreen (
    navController: NavHostController
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Start Game Screen")
        Button(
            onClick = {
                navController.navigate("gameScreen/medium/Player1") // Example navigation action
            }
        ) {
            Text(text = "Start Game")
        }
    }
}