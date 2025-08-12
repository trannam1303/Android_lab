object PrimeFactorCalculator {

    fun primeFactors(int: Int): List<Int> {
        var num = int
        val factors = mutableListOf<Int>()
        var divisor = 2
        while (num > 1) {
            while (num % divisor == 0) {
                factors.add(divisor)
                num /= divisor
            }
            divisor++
        }
        return factors
    }

    fun primeFactors(long: Long): List<Long> {
        var num = long
        val factors = mutableListOf<Long>()
        var divisor = 2L
        while (num > 1) {
            while (num % divisor == 0L) {
                factors.add(divisor)
                num /= divisor
            }
            divisor++
        }
        return factors
    }
}
