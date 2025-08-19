package hu.bme.aut.android.snake.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.android.snake.feature.main.MainScreen
import hu.bme.aut.android.snake.feature.main.MainViewModel

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
        composable("mainscreen") {
            mainViewModel.updateTitle("Snake", false)
            MainScreen(navController = navController)
        }
        //preGameScreen

        //mainscreen

        //highscores

        //settings
    }
}