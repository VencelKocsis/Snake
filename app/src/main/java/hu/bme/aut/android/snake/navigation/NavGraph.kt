package hu.bme.aut.android.snake.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.android.snake.feature.highscores.HighScoresScreen
import hu.bme.aut.android.snake.feature.main.MainScreen
import hu.bme.aut.android.snake.feature.main.MainViewModel
import hu.bme.aut.android.snake.feature.settings.SettingsScreen

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

        //preGameScreen

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