package hu.bme.aut.android.snake.draw.board

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawBoard(
    cellSize: Float,
    cellColor: Color,
    borderCellColor: Color,
    gridWidth: Int,
    gridHeight: Int
) {
    for (i in 0 until gridWidth) {
        for (j in 0 until gridHeight) {
            val isBorderCell = i == 0 || i == gridWidth - 1 || j == 0 || j == gridHeight - 1
            drawRect(
                color = if (isBorderCell) borderCellColor else cellColor,
                topLeft = Offset(i * cellSize, j * cellSize),
                size = Size(cellSize, cellSize)
            )
        }
    }
}