object Pangram {

    fun isPangram(sentence: String): Boolean {
        val letters = sentence
            .lowercase()
            .filter { it in 'a'..'z' }
            .toSet()
        return letters.size == 26
    }
}
