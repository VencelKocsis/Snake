package hu.bme.aut.android.snake.feature.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hu.bme.aut.android.snake.model.Direction
import hu.bme.aut.android.snake.model.SnakeEvent
import hu.bme.aut.android.snake.model.SnakeState
import hu.bme.aut.android.snake.R
import hu.bme.aut.android.snake.draw.board.drawBoard
import hu.bme.aut.android.snake.draw.food.drawFood
import hu.bme.aut.android.snake.draw.snake.drawSnake
import hu.bme.aut.android.snake.model.GameState

@Composable
fun GameScreen(
    state: SnakeState,
    onEvent: (SnakeEvent) -> Unit,
    navController: NavController
) {
    val imageBitmap = ImageBitmap.imageResource(id = R.mipmap.food)
    val snakeHeadBitmap = when(state.direction){
        Direction.DOWN-> ImageBitmap.imageResource(id = R.mipmap.snakehead1)
        Direction.LEFT-> ImageBitmap.imageResource(id = R.mipmap.snakehead2)
        Direction.UP -> ImageBitmap.imageResource(id = R.mipmap.snakehead3)
        Direction.RIGHT-> ImageBitmap.imageResource(id = R.mipmap.snakehead4)
    }

    val turnPartsMap = mapOf(
        Pair("upright", ImageBitmap.imageResource(id = R.mipmap.upright)),
        Pair("rightdown", ImageBitmap.imageResource(id = R.mipmap.rightdown)),
        Pair("downright", ImageBitmap.imageResource(id = R.mipmap.downright)),
        Pair("downleft", ImageBitmap.imageResource(id = R.mipmap.downleft))
    )

    val bodyPartsMap = mapOf(
        Pair("up", ImageBitmap.imageResource(id = R.mipmap.bodyup)),
        Pair("down", ImageBitmap.imageResource(id = R.mipmap.bodydown)),
        Pair("left", ImageBitmap.imageResource(id = R.mipmap.bodyleft)),
        Pair("right", ImageBitmap.imageResource(id = R.mipmap.bodyright))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        //Score and Back Button Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onEvent(SnakeEvent.ResetGame)
                    navController.navigate("mainscreen") {
                        popUpTo("mainscreen") {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        }
        Text(
            text = when {
                state.gameState == GameState.PAUSED -> {
                    "Paused"
                }
                state.isGameOver -> {
                    "Game Over!"
                }
                else -> {
                    "Score: ${state.snake.size - 1}"
                }
            },
            fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal)),
            color = Color.Black,
            fontSize = 30.sp,
            modifier = Modifier
                .weight(10f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))

        //Canvas
        Canvas(modifier = Modifier
            .background(Color.Black)
            .aspectRatio(2 / 3f)
        ) {
            drawBoard(
                cellSize = size.width / 20,
                cellColor = Color.Black,
                borderCellColor = Color.DarkGray,
                gridWidth = state.xSize,
                gridHeight = state.ySize
            )
            drawFood(
                foodImage = imageBitmap,
                cellSize = (size.width / 20).toInt(),
                coordinate = state.food
            )
            drawSnake(
                snakeHeadImage = snakeHeadBitmap,
                cellSize = size.width / 20,
                snake = state.snake,
                turnParts = turnPartsMap,
                bodyParts = bodyPartsMap
            )
        }

        //Controllers
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            //Column 1
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .border(1.dp, Color.Black)
            ) {
                //TextButton 1
                TextButton(
                    onClick = {
                        when (state.gameState) {
                            GameState.IDLE -> onEvent(SnakeEvent.StartGame)
                            GameState.STARTED -> onEvent(SnakeEvent.PauseGame)
                            GameState.PAUSED -> onEvent(SnakeEvent.StartGame)
                        }
                    },
                    Modifier.fillMaxSize()
                ) {
                    Text(
                        text = when (state.gameState) {
                            GameState.IDLE -> "Start"
                            GameState.STARTED -> "Pause"
                            GameState.PAUSED -> "Resume"
                        },
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal)),
                        textAlign = TextAlign.Center
                    )
                }
            }

            //Column 2
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
                    .border(1.dp, Color.Black)
            ) {
                //Row 2.1
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .border(1.dp, Color.Black)
                ) {
                    //IconButton 2.1.1
                    IconButton(
                        onClick = {
                            onEvent(SnakeEvent.ChangeDir(Direction.UP))
                        },
                        modifier = Modifier
                            .background(Color.Gray)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(60.dp, 60.dp)
                                .rotate(0f),
                            tint = Color.Black,
                            painter = painterResource(id = R.mipmap.arrow),
                            contentDescription = null
                        )
                    }
                }

                //Row 2.2
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .border(1.dp, Color.Black)
                ) {
                    //IconButton 2.2.1
                    IconButton(
                        onClick = {
                            onEvent(SnakeEvent.ChangeDir(Direction.LEFT))
                        },
                        modifier = Modifier
                            .background(Color.Gray)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(60.dp, 60.dp)
                                .rotate(-90f),
                            tint = Color.Black,
                            painter = painterResource(id = R.mipmap.arrow),
                            contentDescription = null
                        )
                    }

                    //IconButton 2.2.2
                    IconButton(
                        onClick = {
                            onEvent(SnakeEvent.ChangeDir(Direction.RIGHT))
                        },
                        modifier = Modifier
                            .background(Color.Gray)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(60.dp, 60.dp)
                                .rotate(90f),
                            tint = Color.Black,
                            painter = painterResource(id = R.mipmap.arrow),
                            contentDescription = null
                        )
                    }
                }

                //Row 2.3
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .border(1.dp, Color.Black)
                ) {
                    //IconButton 2.3.1
                    IconButton(
                        onClick = {
                            onEvent(SnakeEvent.ChangeDir(Direction.DOWN))
                        },
                        modifier = Modifier
                            .background(Color.Gray)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(60.dp, 60.dp)
                                .rotate(180f),
                            tint = Color.Black,
                            painter = painterResource(id = R.mipmap.arrow),
                            contentDescription = null
                        )
                    }
                }
            }

            //Column 3
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .border(1.dp, Color.Black)
            ) {
                //TextButton 3.2
                TextButton(
                    onClick = {
                        onEvent(SnakeEvent.ResetGame)
                    },
                    Modifier.fillMaxSize()
                ) {
                    Text(
                        text = if (state.isGameOver) "Restart" else "New Game",
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.pixelfont, style = FontStyle.Normal)),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}