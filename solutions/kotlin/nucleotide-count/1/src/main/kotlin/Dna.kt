class Dna(val dna: String) {

    val nucleotides = mapOf('A' to 0, 'C' to 0, 'G' to 0, 'T' to 0)
	
	init {
	    require(!Regex("[^${nucleotides.keys.joinToString("")}]").containsMatchIn(dna))
	}
	
    val nucleotideCounts: Map<Char, Int>
        get() = nucleotides.mapValues { 
	        val nucleotide = it.key
			dna.count { it == nucleotide } 
		}
}