object CollatzCalculator {
    fun computeStepCount(start: Int): Int {
        require(start > 0) { "Only positive integers are allowed" }

        var steps = 0
        var n = start

        while (n != 1) {
            n = if (n % 2 == 0) {
                n / 2
            } else {
                3 * n + 1
            }
            steps++
        }

        return steps
    }
}
