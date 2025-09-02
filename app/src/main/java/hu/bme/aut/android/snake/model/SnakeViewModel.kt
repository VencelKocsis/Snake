package hu.bme.aut.android.snake.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.android.snake.feature.startgame.GameDifficulty
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SnakeViewModel(): ViewModel() {
    //State
    private val _state = MutableStateFlow((SnakeState()))
    val state = _state.asStateFlow()

    //Game Difficulty
    private val _gameDifficulty = MutableStateFlow(GameDifficulty.EASY)
    val gameDifficulty: StateFlow<GameDifficulty> = _gameDifficulty.asStateFlow()

    //Player Name
    private var playerName: String = ""

    //##FUNS##

    //Event Handling
    fun onEvent(event: SnakeEvent) {
        when (event) {
            //Pause Game
            is SnakeEvent.PauseGame -> {
                _state.update { it.copy(gameState = GameState.PAUSED)}
            }
            //Reset Game
            is SnakeEvent.ResetGame -> {
                _state.value = SnakeState()
            }
            //Start Game
            is SnakeEvent.StartGame -> {
                _state.update { it.copy(gameState = GameState.STARTED)}
                startGame()
            }
            //Direction Handle
            is SnakeEvent.ChangeDir -> {
                //PASS IF GAME NOT STARTED
                if (state.value.gameState != GameState.STARTED) return

                updateDir(
                    when {
                        event.dir == Direction.UP && state.value.direction != Direction.DOWN -> Direction.UP
                        event.dir == Direction.DOWN && state.value.direction != Direction.UP -> Direction.DOWN
                        event.dir == Direction.LEFT && state.value.direction != Direction.RIGHT -> Direction.LEFT
                        event.dir == Direction.RIGHT && state.value.direction != Direction.LEFT -> Direction.RIGHT
                        else -> state.value.direction
                    }
                )
            }
        }
    }

    //Set Game Difficulty
    fun setGameDifficulty(difficulty: GameDifficulty) {
        _gameDifficulty.value = difficulty
    }

    //Set Player Name
    fun setPlayerName(name: String) {
        playerName = name
    }

    //Start Game
    private fun startGame(){
        if (!state.value.isGameOver){
            _state.update { it.copy(gameState = GameState.STARTED) }
            viewModelScope.launch {
                gameDifficulty.collect { difficulty ->
                    while (state.value.gameState == GameState.STARTED) {
                        val delayMillis = when (difficulty) {
                            GameDifficulty.EASY -> 320L
                            GameDifficulty.MEDIUM -> 220L
                            GameDifficulty.HARD -> 120L
                        }
                        _state.value = updateGame(state.value)
                        delay(delayMillis)
                    }
                }
            }
        }
    }

    //Resume Game
    private fun resumeGame() {
        _state.update { it.copy(gameState = GameState.STARTED) }
        startGame()
    }

    //Direction Update
    private fun updateDir(dir: Direction) {
        _state.update { it.copy(direction = dir) }
    }

    //Game Update
    private fun updateGame(state: SnakeState): SnakeState {
        // GAME OVER CHECK
        if (state.isGameOver) {
            return state
        }

        //HEAD, GRID SIZE
        val head = state.snake.first()
        val xGrid = state.xSize
        val yGrid = state.ySize

        //NEW HEAD COORDINATE
        val newHead = when(state.direction) {
            Direction.UP -> Coordinate(x = head.x, y = (head.y - 1))
            Direction.DOWN -> Coordinate(x = head.x, y = (head.y + 1))
            Direction.LEFT -> Coordinate(x = (head.x - 1), y = head.y)
            Direction.RIGHT -> Coordinate(x = (head.x + 1), y = head.y)
        }

        //GAME OVER - SNAKE HIT ITSELF OR WALL
        if (
            state.snake.contains(newHead) ||
            !isInBounds(newHead, xGrid, yGrid)
        ) {
            //DATABASE INSERT
            return state.copy(isGameOver = true)
        }

        //NEW SNAKE AND FOOD
        var newSnake = mutableListOf(newHead) + state.snake
        val newFood = if (newHead == state.food) {
            SnakeState.generateFood(xGrid, yGrid)
        } else {
            state.food
        }

        if (newHead != state.food) {
            newSnake = newSnake.toMutableList()
            newSnake.removeAt(newSnake.lastIndex)
        }

        return state.copy(snake = newSnake, food = newFood)
    }

    //CHECK IF COORDINATE IS IN BOUNDS
    private fun isInBounds(coordinate: Coordinate, xGridSize: Int, yGridSize: Int): Boolean {
        return coordinate.x in 1 until xGridSize-1 && coordinate.y in 1 until yGridSize-1
    }
}