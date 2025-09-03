package hu.bme.aut.android.snake.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.android.snake.R
import hu.bme.aut.android.snake.feature.settings.SettingsViewModel
import hu.bme.aut.android.snake.model.SnakeViewModel
import hu.bme.aut.android.snake.navigation.NavGraph
import hu.bme.aut.android.snake.ui.theme.DarkGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavScreen(
    mainViewModel: MainViewModel,
    snakeViewModel: SnakeViewModel,
    settingsViewModel: SettingsViewModel
) {
    val isSensorControlled by settingsViewModel.isSensorControlled.collectAsStateWithLifecycle()
    snakeViewModel.setSensorControlled(isSensorControlled)

    //TopBar
    val title by mainViewModel.title.observeAsState("Snake")
    val navigationLambda by mainViewModel.navigate.observeAsState()
    val showBackArrow by mainViewModel.showBackArrow.observeAsState(false)

    Scaffold(
        topBar = {
            if (title != "") {
                TopAppBar(
                    //NavigationIcon
                    navigationIcon = {
                        if (showBackArrow) {
                            IconButton(
                                onClick = {
                                    navigationLambda?.invoke()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = White
                                )
                            }
                        }
                    },
                    //Actions
                    actions = {
                        IconButton(
                            onClick = {
                                // TODO
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = White
                            )
                        }
                    },
                    //Title
                    title = {
                        Text(
                            text = title,
                            color = White,
                            fontSize = 30.sp,
                            fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal))
                        )
                    },
                    //Colors
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkGrey)
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val navController = rememberNavController()
        val state = snakeViewModel.state.collectAsStateWithLifecycle()

        NavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            snakeViewmodel = snakeViewModel,
            settingsViewModel = settingsViewModel,
            state = state.value
        )
    }
}