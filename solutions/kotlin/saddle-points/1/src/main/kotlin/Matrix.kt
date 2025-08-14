data class MatrixCoordinate(val row: Int, val col: Int)

class Matrix(private val matrix: List<List<Int>>) {

    val saddlePoints: Set<MatrixCoordinate>
        get() {
            val points = mutableSetOf<MatrixCoordinate>()
            for (row in matrix.indices) {
                for (col in matrix[row].indices) {
                    if (isSaddlePoint(row, col)) {
                        points.add(MatrixCoordinate(row + 1, col + 1))
                    }
                }
            }

            return points
        }

    private fun isSaddlePoint(row: Int, col: Int): Boolean {
        val value = matrix[row][col]
        return isLargestInRow(value, row) && isSmallestInColumn(value, col)
    }

    private fun isLargestInRow(value: Int, row: Int): Boolean {
        return matrix[row].maxOrNull() == value
    }

    private fun isSmallestInColumn(value: Int, col: Int): Boolean {
        return matrix.map { it[col] }.minOrNull() == value
    }
}