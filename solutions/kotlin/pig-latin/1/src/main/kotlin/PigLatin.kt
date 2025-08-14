class PigLatin {
    companion object {

        val vowel = Regex("(\\s*)(a|e|i|o|u|yt|xr)(\\w+)")
        val consonant = Regex("(\\s*)(ch|qu|thr|th|sch|yt|rh|\\wqu|\\w)(\\w+)")

        fun translate (input : String) : String {
            if (vowel.matches(input)) 
                return vowel.replace(input, "\$1\$2\$3ay")
            else
                return consonant.replace(input, "\$1\$3\$2ay")
        }
    }
}