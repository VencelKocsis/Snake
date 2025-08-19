package hu.bme.aut.android.snake.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.bme.aut.android.snake.R
import androidx.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController
) {
    Column (
        modifier = Modifier
            .background(Color.Gray)
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //Title
        Text(
            text = "Snake Game",
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal)),
            modifier = Modifier.padding(16.dp)
        )

        //Button 1 preGameScreen
        Button(
            onClick = {
                navController.navigate("preGameScreen")
            },
            colors = ButtonDefaults.buttonColors(
                Color.Black
            ),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Start Game",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal))
            )
        }

        //Button 2 highScoreScreen
        Button(
            onClick = {
                navController.navigate("highScores")
            },
            colors = ButtonDefaults.buttonColors(
                Color.Black
            ),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "High Scores",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal))
            )
        }

        //Button3 settings
        Button(
            onClick = {
                navController.navigate("settings")
            },
            colors = ButtonDefaults.buttonColors(
                Color.Black
            ),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Settings",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal))
            )
        }
    }
}