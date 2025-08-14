import Orientation.*

class Robot (var gridPosition: GridPosition = GridPosition(0,0),
             var orientation: Orientation = NORTH){

    fun turnRight() {
        orientation  = values()[(orientation.ordinal + 1) % 4]
    }

    fun turnLeft() {
        val new  =
                when (orientation) {
                    NORTH -> WEST.ordinal
                    else -> orientation.ordinal - 1
                }
        orientation = values()[new]
    }

    fun advance() {
        gridPosition =
                when (orientation) {
                    NORTH -> GridPosition(gridPosition.x, gridPosition.y + 1)
                    EAST -> GridPosition(gridPosition.x + 1, gridPosition.y)
                    SOUTH -> GridPosition(gridPosition.x, gridPosition.y - 1)
                    WEST -> GridPosition(gridPosition.x - 1, gridPosition.y)
                }
    }

    fun simulate(s: String) {
        s.forEach {
            when (it) {
                'L' -> turnLeft()
                'R' -> turnRight()
                'A' -> advance()
            }
        }
    }

}