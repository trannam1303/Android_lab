private const val SUBSTITUTION_MARK = '*'

class RailFenceCipher(private val key: Int) {

    fun getEncryptedData(input: String) = railDecryptedData(input).joinToString("")

    private fun railDecryptedData(data: String): List<String> {
        var down = false
        var currentRail = 0
        return data.fold(MutableList(key) { "" }) { rails, character ->
            if (currentRail == 0 || currentRail == key - 1) down = !down

            rails[currentRail] = rails[currentRail] + character.toString()

            if (down) currentRail++ else currentRail--

            rails
        }
    }

    fun getDecryptedData(input: String) = extractData(railEncryptedData(input))

    private fun railEncryptedData(input: String): List<String> {
        val markedRails = generateRailsWithSubstitutionMarks(input.length)
        return replaceMarksWithData(input, markedRails)
    }

    private fun generateRailsWithSubstitutionMarks(dataLength: Int): List<String> {
        val emptyRails = emptyRails(railLength = dataLength)
        return markCharacterPlacesInRails(emptyRails)
    }

    private fun emptyRails(railLength: Int) = MutableList(key) { " ".repeat(railLength) }

    private fun markCharacterPlacesInRails(emptyRails: List<String>): List<String> {
        val dataLength = emptyRails[0].length
        var down = false
        var currentRail = 0
        return (0 until dataLength).fold(emptyRails.toMutableList()) { rails, column ->
            if (currentRail == 0 || currentRail == key - 1) down = !down

            rails[currentRail] = rails[currentRail].replaceRange(column until column + 1, SUBSTITUTION_MARK.toString())

            if (down) currentRail++ else currentRail--

            rails
        }
    }

    private fun replaceMarksWithData(data: String, markedRails: List<String>): List<String> {
        var start = 0
        return markedRails.map { rail ->
            val marksCount = rail.count { it == SUBSTITUTION_MARK }
            val end = start + marksCount
            var dataRail = rail

            (start until end).forEach { index ->
                dataRail = dataRail.replaceFirst(SUBSTITUTION_MARK, data[index])
            }

            start += marksCount
            dataRail
        }
    }

    private fun extractData(rails: List<String>): String {
        var down = false
        var currentRail = 0
        return (0 until rails[0].length).fold("") { data, column ->
            if (currentRail == 0 || currentRail == key - 1) down = !down

            val nextCharacter = rails[currentRail][column].toString()

            if (down) currentRail++ else currentRail--

            data + nextCharacter
        }
    }
}