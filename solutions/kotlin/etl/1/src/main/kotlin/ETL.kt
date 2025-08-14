object ETL {
    fun transform(source: Map<Int, Collection<Char>>): Map<Char, Int> {
        return source.flatMap { (k, v) ->
            v.map { it.lowercaseChar() to k }
        }.toMap()
    }
}