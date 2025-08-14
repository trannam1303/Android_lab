object PascalsTriangle {

  fun computeTriangle(rows: Int): List<List<Int>> {
    require(rows >= 0) { "Rows can't be negative!" }

    return generateSequence(listOf(1)) { prev ->
      listOf(1) + prev.windowed(2).map { it.sum() } + listOf(1)
    }.take(rows).toList()
  }
}