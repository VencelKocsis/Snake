package hu.bme.aut.android.snake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.snake.feature.main.MainNavScreen
import hu.bme.aut.android.snake.feature.main.MainViewModel
import hu.bme.aut.android.snake.model.SnakeViewModel
import hu.bme.aut.android.snake.ui.theme.SnakeTheme

@AndroidEntryPoint
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