object RunLengthEncoding {

    fun encode(input: String): String {
        if (input.isEmpty()) return ""

        val sb = StringBuilder()
        var count = 1

        for (i in 1..input.length) {
            if (i < input.length && input[i] == input[i - 1]) {
                count++
            } else {
                if (count > 1) sb.append(count)
                sb.append(input[i - 1])
                count = 1
            }
        }
        return sb.toString()
    }

    fun decode(input: String): String {
        if (input.isEmpty()) return ""

        val sb = StringBuilder()
        var count = ""
        for (char in input) {
            if (char.isDigit()) {
                count += char
            } else {
                val repeatCount = if (count.isEmpty()) 1 else count.toInt()
                repeat(repeatCount) { sb.append(char) }
                count = ""
            }
        }
        return sb.toString()
    }
}
