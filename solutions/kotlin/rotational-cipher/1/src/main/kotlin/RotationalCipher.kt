class RotationalCipher(val key: Int) {

    fun encode(text: String) =
        text
            .map { if (it.isLetter()) it.rot() else it }
            .joinToString("")

    fun Char.base() =
        if (isUpperCase()) 'A' else 'a'

    fun Char.rot() =
        base() + (this - base() + key) % 26
}