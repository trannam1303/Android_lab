class ChangeCalculator(private val coins: List<Int>) {
    init { require(coins.isNotEmpty() && coins.all { it > 0 }) }

    fun computeMostEfficientChange(grandTotal: Int): List<Int> {
        if (grandTotal < 0)
            throw IllegalArgumentException("Negative totals are not allowed.")
        if (grandTotal < coins.min() && grandTotal > 0)
            throw IllegalArgumentException("The total $grandTotal cannot be represented in the given currency.")

        val paths = MutableList(grandTotal + 1) { MutableList(grandTotal + 1) { 0 } }
        paths[0] = emptyList<Int>().toMutableList()
        paths.forEachIndexed { index, _ ->
            coins.forEach { coin ->
                val remaining = index - coin
                if (remaining >= 0 && paths[remaining].size < paths[index].size)
                    paths[index] = paths[remaining].plusElement(coin).toMutableList()
            }
        }
        if (paths[grandTotal].size > grandTotal)
            throw IllegalArgumentException("The total $grandTotal cannot be represented in the given currency.")
        return paths[grandTotal]
    }
}