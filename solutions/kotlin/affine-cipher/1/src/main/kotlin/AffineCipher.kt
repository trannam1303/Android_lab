object AffineCipher {

    fun encode(input: String, a: Int, b: Int): String {
        require(checkCoprime(a))

        val trimmed = input
            .toLowerCase()
            .replace("\\W".toRegex(), "")

        return trimmed
            .map { encryptLetter(it, a, b) }
            .chunked(5)
            .joinToString(separator = " ") {
                it.joinToString(separator = "")
            }
    }

    fun decode(input: String, a: Int, b: Int): String {
        require(checkCoprime(a))

        val trimmed = input.replace(" ", "")

        return trimmed
            .map { decryptLetter(it, a, b) }
            .joinToString(separator = "")
    }

    private fun encryptLetter(letter: Char, a: Int, b: Int): Char {
        if (letter.isDigit()) return letter

        val x = letter.toInt() - 'a'.toInt()
        val e = ((a * x) + b) % 26
        return (e + 'a'.toInt()).toChar()
    }

    private fun decryptLetter(letter: Char, a: Int, b: Int): Char {
        if (letter.isDigit()) return letter

        val aInv = mmi(a)
        val y = letter.toInt() - 'a'.toInt()
        var d = (aInv * (y - b)) % 26

        if (d < 0) d += 26

        return (d + 'a'.toInt()).toChar()
    }

    private fun mmi(a: Int): Int = (0 until 26).first { it * a % 26 == 1 }

    private fun checkCoprime(a: Int): Boolean = gcd(a) == 1

    private fun gcd(a: Int): Int {
        var x = a
        var y = 26
        while (y != 0) {
            val t = x % y
            x = y
            y = t
        }

        return x
    }
}
