class Series(input: String) {

  private val digits = input.map { require(it.isDigit()); it.digitToInt() }

  fun getLargestProduct(span: Int): Long {
    require(span >= 0) { "Series span must be non-negative" }
    require(digits.size >= span) { "Series input must be at least $span digits long" }

    return if (span == 0) 1
    else digits.windowed(span, 1).maxOf { it.fold(1L) { acc, i -> acc * i } }
  }
}