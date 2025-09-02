package hu.bme.aut.android.snake.draw.snake

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import hu.bme.aut.android.snake.model.Coordinate

fun DrawScope.drawSnake(
    snakeHeadImage: ImageBitmap,
    cellSize: Float,
    snake: List<Coordinate>,
    turnParts: Map<String, ImageBitmap>,
    bodyParts: Map<String, ImageBitmap>
) {
    val size = cellSize.toInt()
    snake.forEachIndexed { index, coordinate ->
        when {
            //SnakeHead Draw
            index == 0 -> {
                drawImage(
                    image = snakeHeadImage,
                    dstOffset = IntOffset(
                        coordinate.x * size,
                        coordinate.y * size
                    ),
                    dstSize = IntSize(size, size)
                )
            }

            //SnakeTail Draw
            index == snake.size - 1 ->
            drawCircle(
                color = Color.Green,
                center = Offset(
                    x = coordinate.x * size.toFloat() + size / 2,
                    y = coordinate.y * size.toFloat() + size / 2
                ),
                radius = size / 2f
            )

            //Turn Segment Draw
            isTurnSegment(index, snake) -> {
                drawImage(
                    image = getTurnImage(
                        index,
                        snake,
                        turnUpRightImage = turnParts["upright"]!!,
                        turnUpLeftImage = turnParts["rightdown"]!!,
                        turnDownRightImage = turnParts["downright"]!!,
                        turnDownLeftImage = turnParts["downleft"]!!
                    ),
                    dstOffset = IntOffset(coordinate.x * size, coordinate.y * size),
                    dstSize = IntSize(size, size)
                )
            }

            else -> {
                //Body Part Draw
                drawImage(
                    image = getBodyImage(
                        index,
                        snake,
                        bodyVerticalImage = bodyParts["up"]!!,
                        bodyHorizontalImage = bodyParts["right"]!!
                    ),
                    dstOffset = IntOffset(coordinate.x * size, coordinate.y * size),
                    dstSize = IntSize(size, size)
                )
            }
        }
    }
}

fun getBodyImage(
    index: Int,
    snake: List<Coordinate>,
    bodyVerticalImage: ImageBitmap,
    bodyHorizontalImage: ImageBitmap
) : ImageBitmap {
    val prev = snake[index - 1]
    val curr = snake[index]
    val next = snake[index + 1]

    val prevDirection = Pair(curr.x - prev.x, curr.y - prev.y)
    val nextDirection = Pair(next.x - curr.x, next.y - curr.y)

    return when {
        prevDirection.first == 0 && nextDirection.first == 0 -> bodyVerticalImage // Both directions are vertical
        prevDirection.second == 0 && nextDirection.second == 0 -> bodyHorizontalImage // Both directions are horizontal
        else -> throw IllegalArgumentException("Unexpected body segment at index $index")
    }
}

fun isTurnSegment(index: Int, snake: List<Coordinate>): Boolean {
    if (index <= 0 || index >= snake.size - 1) return false

    val prev = snake[index - 1]
    val curr = snake[index]
    val next = snake[index + 1]

    val prevDirection = Pair(curr.x - prev.x, curr.y - prev.y)
    val nextDirection = Pair(next.x - curr.x, next.y - curr.y)

    return (prevDirection.first == 0 && nextDirection.second == 0) &&
            (prevDirection.second == 0 && nextDirection.first == 0)
}

fun getTurnImage(
    index: Int,
    snake: List<Coordinate>,
    turnUpRightImage: ImageBitmap,
    turnUpLeftImage: ImageBitmap,
    turnDownRightImage: ImageBitmap,
    turnDownLeftImage: ImageBitmap
) : ImageBitmap {
    val prev = snake[index - 1]
    val curr = snake[index]
    val next = snake[index + 1]

    val prevDirection = Pair(curr.x - prev.x, curr.y - prev.y)
    val nextDirection = Pair(next.x - curr.x, next.y - curr.y)

    return when {
        prevDirection == Pair(0, -1) && nextDirection == Pair(1, 0) -> turnUpRightImage // UP to RIGHT
        prevDirection == Pair(0, -1) && nextDirection == Pair(-1, 0) -> turnUpLeftImage  // UP to LEFT
        prevDirection == Pair(0, 1) && nextDirection == Pair(1, 0) -> turnDownRightImage // DOWN to RIGHT
        prevDirection == Pair(0, 1) && nextDirection == Pair(-1, 0) -> turnDownLeftImage // DOWN to LEFT
        prevDirection == Pair(1, 0) && nextDirection == Pair(0, -1) -> turnDownLeftImage // RIGHT to UP
        prevDirection == Pair(1, 0) && nextDirection == Pair(0, 1) -> turnUpLeftImage   // RIGHT to DOWN
        prevDirection == Pair(-1, 0) && nextDirection == Pair(0, -1) -> turnDownRightImage // LEFT to UP
        prevDirection == Pair(-1, 0) && nextDirection == Pair(0, 1) -> turnUpRightImage  // LEFT to DOWN
        else -> throw IllegalArgumentException("Unexpected turn segment")
    }
}