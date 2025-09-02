package hu.bme.aut.android.snake.draw.food

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import hu.bme.aut.android.snake.model.Coordinate

fun DrawScope.drawFood(
    foodImage: ImageBitmap,
    cellSize: Int,
    coordinate: Coordinate
    ) {
    drawImage(
        image = foodImage,
        dstOffset = IntOffset(coordinate.x * cellSize, coordinate.y * cellSize),
        dstSize = IntSize(cellSize, cellSize)
    )
}