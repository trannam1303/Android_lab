import kotlin.math.min

fun translate(rna: String?): List<String> {
  if (rna == null)
    return listOf()

  val map = mapOf(
    "AUG" to "Methionine",
    "UUU" to "Phenylalanine",
    "UUC" to "Phenylalanine",
    "UUA" to "Leucine",
    "UUG" to "Leucine",
    "UCU" to "Serine",
    "UCC" to "Serine",
    "UCA" to "Serine",
    "UCG" to "Serine",
    "UAU" to "Tyrosine",
    "UAC" to "Tyrosine",
    "UGU" to "Cysteine",
    "UGC" to "Cysteine",
    "UGG" to "Tryptophan",
    "UAA" to "STOP",
    "UAG" to "STOP",
    "UGA" to "STOP",
  )

  val n = if (rna == null) 0 else rna.length
  val arr: MutableList<String> = mutableListOf()

  for (i in 0..<n step 3) {
    val stop = min(i + 3, n)
    val substr = rna?.substring(i, stop)
    val elem = map.get(substr)

    if (elem == null) {
      throw IllegalArgumentException("Invalid codon")
    } else if (elem == "STOP") {
      break
    }

    arr.add(elem)
  }

  return arr
}