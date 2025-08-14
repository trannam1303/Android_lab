object SpiralMatrix {

    enum class Direction {
        RIGHT, DOWN, LEFT, UP
    }


    data class Coordinate (val value: Int, val x: Int, val y: Int)

    fun Direction.next(): Direction {
        val directions = Direction.values()
        val index = directions.indexOf(this)
        return directions[if (index + 1 == directions.size) 0 else index + 1]
    }

    class SequenceGenerator(private val size: Int) {

        val coordinates = sequence {

            var x = 0
            var y = -1

            numberAndDirection.iterator().forEach  {
                val (value, direction) = it

                x = when (direction) {
                    Direction.DOWN -> x + 1
                    Direction.UP -> x - 1
                    else -> x
                }

                y = when (direction) {
                    Direction.LEFT -> y - 1
                    Direction.RIGHT -> y + 1
                    else -> y
                }

                yield(Coordinate(value, x, y))
            }
        }

        private val numberAndDirection = sequence {
            var n = size
            var currentStepInN = size
            var currentDirection = Direction.RIGHT

            for (i in 0 until size * size) {
                yield(i to currentDirection)

                currentStepInN++

                if (currentStepInN == n || currentStepInN == 2 * n) {
                    currentDirection = currentDirection.next()
                }

                if (currentStepInN == 2 * n) {
                    currentStepInN = 0
                    n--
                }
            }
        }
    }


    fun ofSize(size: Int): Array<IntArray> {

        val array = Array(size) {IntArray(size)}
        SequenceGenerator(size).coordinates.forEach {
           array[it.x][it.y] = it.value + 1
        }

        return array
    }
}