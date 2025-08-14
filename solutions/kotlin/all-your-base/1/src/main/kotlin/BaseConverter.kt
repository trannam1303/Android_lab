class BaseConverter(inputBase: Int, inputDigits: IntArray) {
    init {
        require(inputBase >= 2) { "Bases must be at least 2." }
        require(inputDigits.isNotEmpty()) { "You must supply at least one digit." }
        if (inputDigits.size > 1) {
            require(inputDigits[0] != 0) { "Digits may not contain leading zeros." }
        }
    }

    private val n = inputDigits.fold(0) { acc, v ->
        require(v >= 0) { "Digits may not be negative." }
        require(v < inputBase) { "All digits must be strictly less than the base." }
        acc * inputBase + v
    }

    fun convertToBase(base: Int): IntArray {
        require(base >= 2) { "Bases must be at least 2." }
        if (n == 0) return intArrayOf(0)

        val output = mutableListOf<Int>()
        var remaining = n
        while (remaining > 0) {
            output.add(remaining % base)
            remaining /= base
        }
        return output.asReversed().toIntArray()
    }
}