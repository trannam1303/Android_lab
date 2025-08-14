object Luhn {

  fun isValid(candidate: String): Boolean {
    if (candidate.filter { it != ' ' }.length < 2) return false
    if (candidate.any { it !in ('0'..'9') + ' ' }) return false

    return candidate
      .filter { it != ' ' }
      .map { it.digitToInt() }
      .let { if (it.size % 2 == 0) it else listOf(0) + it }
      .windowed(2, 2)
      .sumOf { w -> (w.first() * 2).let { if (it > 9) it - 9 else it } + w.last() } % 10 == 0
  }
}