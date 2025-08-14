object Prime {
    fun nth(n: Int): Int {
        require(n > 0) { "There is no zeroth prime." }
        val primes = mutableListOf(2)
        return generateSequence(2) { it + 1 }
            .filter { candidate ->
                if ((candidate and 1 == 1) && primes.none { prime -> candidate % prime == 0 }) {
                    primes.add(candidate)
                }
                candidate in primes
            }
            .elementAt(n - 1)
    }
}