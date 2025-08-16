object BeerSong {
    fun verses(startBottles: Int, takeDown: Int): String {
        val verses = (startBottles downTo takeDown).map { nrOfBottles ->
            val currentBottlePhrase = bottlePhrase(nrOfBottles)
            val instruction = instruction(nrOfBottles)
            val nextBottlePhrase = bottlePhrase(nextNrOfBottles(nrOfBottles)).toLowerCase()
            "$currentBottlePhrase of beer on the wall, ${currentBottlePhrase.toLowerCase()} of beer.\n$instruction, $nextBottlePhrase of beer on the wall.\n"
        }

        return verses.joinToString("\n")
    }

    private fun bottlePhrase(nrOfBottles: Int) = when (nrOfBottles) {
        0 -> "No more bottles"
        1 -> "1 bottle"
        else -> "$nrOfBottles bottles"
    }

    private fun instruction(nrOfBottles: Int) = when (nrOfBottles) {
        0 -> "Go to the store and buy some more"
        1 -> "Take it down and pass it around"
        else -> "Take one down and pass it around"
    }

    private fun nextNrOfBottles(nrOfBottles: Int) = when (nrOfBottles) {
        0 -> 99
        else -> nrOfBottles - 1
    }
}