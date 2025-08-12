object Bob {
    fun hey(input: String): String {
        val trimmed = input.trim()

        // Silence
        if (trimmed.isEmpty()) {
            return "Fine. Be that way!"
        }

        val isQuestion = trimmed.endsWith("?")
        val isYelling = trimmed.any { it.isLetter() } && trimmed == trimmed.uppercase()

        return when {
            isQuestion && isYelling -> "Calm down, I know what I'm doing!"
            isYelling -> "Whoa, chill out!"
            isQuestion -> "Sure."
            else -> "Whatever."
        }
    }
}

