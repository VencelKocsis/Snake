package hu.bme.aut.android.snake.model

import kotlin.random.Random

data class SnakeState(
    val xSize: Int = 20,
    val ySize: Int = 30,
    val direction: Direction = Direction.RIGHT,
    val snake: List<Coordinate> = listOf(Coordinate(x =5, y = 5)),
    val food: Coordinate = generateFood(xSize, ySize),
    val isGameOver: Boolean = false,
    val gameState: GameState = GameState.IDLE
) {
    //Companion Object - Generate Food
    companion object {
        fun generateFood(
            xSize: Int,
            ySize: Int
        ): Coordinate {
            return Coordinate(
                x = Random.nextInt(from = 1, until = xSize - 1),
                y = Random.nextInt(from = 1, until = ySize - 1)
            )
        }
    }

    // Get State
    fun getState(): GameState {
        return gameState
    }
}

//Game State
enum class GameState{
    IDLE,
    STARTED,
    PAUSED
}

//Direction
enum class Direction{
    UP,
    DOWN,
    LEFT,
    RIGHT
}

//Coordinate
data class Coordinate(
    val x: Int,
    val y: Int
)