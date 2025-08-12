object Acronym {
    fun generate(phrase: String): String {
        return phrase
            .replace("-", " ") // hyphens become spaces
            .replace(Regex("[^A-Za-z\\s]"), "") // remove all other punctuation
            .split(Regex("\\s+")) // split on spaces
            .filter { it.isNotEmpty() }
            .joinToString("") { it[0].uppercase() }
    }
}
