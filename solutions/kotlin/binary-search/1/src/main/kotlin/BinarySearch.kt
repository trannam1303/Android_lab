object BinarySearch {
    fun search(list: List<Int>, item: Int): Int =
            (list.size / 2).let {
                when {
                    list.isEmpty() -> throw NoSuchElementException()
                    list[it] < item -> (it + 1) + search(list.subList(it + 1, list.size), item)
                    list[it] > item -> search(list.subList(0, it), item)
                    else -> it
                }
            }
}
