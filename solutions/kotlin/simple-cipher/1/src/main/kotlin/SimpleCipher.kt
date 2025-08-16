data class Cipher(var key: String) {
    companion object {
        val getRandomKey = {
            (0 until 100).map {
                'a' + (0..Int.MAX_VALUE).random() % 26
            }.joinToString("")
        }
    }

    constructor() : this(getRandomKey())

    init {
        require(key.isNotEmpty() && key.all { it.isLowerCase() && it.isLetter() })
    }

    fun encode(s: String): String {
        return s.mapIndexed { index, c ->
            'a' + ((c + (key[index % key.length] - 'a') - 'a')) % 26
        }.joinToString("")
    }

    fun decode(s: String): String {
        return s.mapIndexed { index, c ->
            'a' + Math.floorMod(((c - (key[index % key.length] - 'a') - 'a')), 26)
        }.joinToString("")
    }
}