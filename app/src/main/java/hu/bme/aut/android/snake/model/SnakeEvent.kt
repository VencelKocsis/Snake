package hu.bme.aut.android.snake.model

sealed class SnakeEvent {
    data object StartGame: SnakeEvent()
    data object PauseGame: SnakeEvent()
    data object ResetGame: SnakeEvent()
    data class ChangeDir(val dir: Direction): SnakeEvent()
}