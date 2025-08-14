import kotlin.math.ceil
import kotlin.math.sqrt

object CryptoSquare {

    fun ciphertext(plaintext: String): String = plaintext
            .normalize()
            .toRectangleMatrix()
            .transpose()
            .joinToString(" ")

    private fun String.normalize() = this.toLowerCase().filter(Char::isLetterOrDigit)

    private fun String.toRectangleMatrix(): List<String> {
        val c = numberOfColumns(this)
        return this.chunked(c) { it.toString().padEnd(c, ' ') }
    }

    // Using max(x, 1) in case the string is empty
    private fun numberOfColumns(s: String) = maxOf(ceil(sqrt(s.length.toDouble())).toInt(), 1)

    private fun List<String>.transpose(): List<String> =
            (this.elementAtOrElse(0) { "" }.indices).map { col ->
                (this.indices).map { row ->
                    this[row][col]
                }.joinToString("")
            }
}