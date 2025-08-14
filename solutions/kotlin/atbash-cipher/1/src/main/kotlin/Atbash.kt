object Atbash {

    private val Char.ordinal: Int
	    get() = this.toLowerCase() - 'a' + 1
	
	private val Char.encoded: Char
	    get() = when (this.isDigit()) {
		    true -> this
			false -> 'a' + 26 - this.ordinal
		}
		
    fun encode(s: String) = 
	    s.filter { it.isLetterOrDigit() }
		.map { it.encoded }
		.chunked(5) { it.joinToString("") }
		.joinToString(" ")

    fun decode(s: String) = s.replace(" ", "").map { it.encoded }.joinToString("")
}