class Matrix(private val matrixAsString: String) {

    fun column(colNr: Int): List<Int> {
        return matrixAsString.split("\n").map { it.split(" ")[colNr-1].toInt() }
    }

    fun row(rowNr: Int): List<Int> {
        return matrixAsString.split("\n")[rowNr-1].split(" ").map { it.toInt() }
    }
}