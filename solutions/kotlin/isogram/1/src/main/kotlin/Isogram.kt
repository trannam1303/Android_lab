object Isogram {
    fun isIsogram(input: String): Boolean {
        val lettersSeen = mutableSetOf<Char>()
        for (ch in input.lowercase()) {
            if (ch.isLetter()) {
                if (!lettersSeen.add(ch)) {
                    return false // đã thấy chữ này trước đó → không phải isogram
                }
            }
        }
        return true
    }
}
