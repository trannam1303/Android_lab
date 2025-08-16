class NumberSpeller {

    fun say(input: Long): String {
        require(input in 0..999_999_999_999)
        if (input == 0L) return "zero"

        val sliced = input.slice()
        val len = sliced.size

        return sliced.withIndex().asSequence().flatMap { (i, n) ->
            val eng = n.toEnglish().asSequence()
            when {
                n == 0L -> sequenceOf()
                i == len - 1 -> eng
                else -> eng + phrases[len - i - 2]
            }
        }.filter { it.isNotEmpty() }.joinToString(" ")
    }

    companion object {
        val phrases = listOf("thousand", "million", "billion")

        val smalls = listOf(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve"
        )

        val tens = listOf(
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
        )
    }

    private fun Long.slice(): List<Long> {
        var i = this
        val r = mutableListOf<Long>()
        while (i > 0) {
            r.add(0, i % 1000)
            i /= 1000
        }
        return r
    }

    private fun Long.toEnglish(): List<String> {
        require(this < 1000)

        val r = mutableListOf<String>()

        var n = this

        if (n >= 100) {
            r.add("${(this / 100).mapSmall()} hundred")
            n %= 100
        }

        r.add(
            when {
                n <= 12 -> n.mapSmall()
                n in (13..19) -> "${(n % 10).mapSmall()}teen"
                n % 10 == 0L -> tens[n.toInt() / 10 - 2]
                else -> "${(tens[n.toInt() / 10 - 2])}-${(n % 10).mapSmall()}"
            }
        )

        return r
    }

    private fun Long.mapSmall(): String {
        require(this < 10)

        return if (this == 0L) "" else smalls[this.toInt() - 1]
    }
}