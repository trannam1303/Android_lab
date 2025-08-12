import kotlin.math.pow

object ArmstrongNumber {
    fun check(input: Int): Boolean {
        val digits = input.toString().map { it.digitToInt() }
        val power = digits.size
        return digits.sumOf { it.toDouble().pow(power).toInt() } == input
    }
}
