object Transpose {

    fun transpose(input: List<String>): List<String> {
        var rez: MutableList<String> = mutableListOf()
        input.forEachIndexed { i, it  ->
            it.forEachIndexed { index, c ->
                if (rez.size<=index) rez.add(index, "".padEnd(i,' ')+c)
                else rez.set(index, rez[index].padEnd(i,' ')+c)
            }
        }
        return rez
    }
}