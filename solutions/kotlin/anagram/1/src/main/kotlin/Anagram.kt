class Anagram (word: String) {

    private val wordLowered = word.lowercase()
    private val wordSorted = wordLowered.toCharArray().sorted()

    fun match(anagrams: Collection<String>): Set<String> =
        anagrams.filter { 
                val candidate = it.lowercase()
                candidate.length == wordLowered.length && candidate != wordLowered && 
                    wordSorted == candidate.toCharArray().sorted()
        }.toSet()
}