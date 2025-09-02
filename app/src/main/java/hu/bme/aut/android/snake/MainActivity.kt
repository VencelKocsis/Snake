package hu.bme.aut.android.snake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.android.snake.feature.main.MainNavScreen
import hu.bme.aut.android.snake.feature.main.MainViewModel
import hu.bme.aut.android.snake.model.SnakeViewModel
import hu.bme.aut.android.snake.ui.theme.SnakeTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val snakeViewmodel: SnakeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnakeTheme {
                MainNavScreen(
                    mainViewModel = mainViewModel,
                    snakeViewModel = snakeViewmodel
                )
            }
        }
    }
}