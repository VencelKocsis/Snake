package hu.bme.aut.android.snake.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hu.bme.aut.android.snake.feature.game.GameScreen
import hu.bme.aut.android.snake.feature.highscores.HighScoresScreen
import hu.bme.aut.android.snake.feature.main.MainScreen
import hu.bme.aut.android.snake.feature.main.MainViewModel
import hu.bme.aut.android.snake.feature.settings.SettingsScreen
import hu.bme.aut.android.snake.feature.startgame.StartGameScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "mainscreen",
    mainViewModel: MainViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        //gameScreen
        composable(
            "gameScreen/{playerName}/{difficulty}",
            arguments = listOf(
                navArgument("playerName") { type = NavType.StringType },
                navArgument("difficulty") { type = NavType.StringType }
            )
        ) {
            mainViewModel.updateTitle("", false)
            GameScreen(
                name = it.arguments?.getString("playerName") ?: "",
                difficulty = it.arguments?.getString("difficulty") ?: "",
            )
        }

        //preGameScreen
        composable("preGamescreen") {
            mainViewModel.updateTitle("Game Settings", true)
            mainViewModel.setNavigationAction { navController.navigate("mainscreen") }
            StartGameScreen(
                navController = navController
            )
        }

        //mainscreen
        composable("mainscreen") {
            mainViewModel.updateTitle("Snake", false)
            MainScreen(navController = navController)
        }

        //highscores
        composable("highscores") {
            mainViewModel.updateTitle("Highscores", true)
            mainViewModel.setNavigationAction { navController.navigate("mainscreen") }
            HighScoresScreen()
        }

        //settings
        composable("settings") {
            mainViewModel.updateTitle("Settings", true)
            mainViewModel.setNavigationAction { navController.navigate("mainscreen") }
            SettingsScreen()
        }
    }
}