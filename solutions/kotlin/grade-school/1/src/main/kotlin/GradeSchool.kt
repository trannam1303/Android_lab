class School {

    private var roster = emptyList<Pair<String,Int>>().toMutableList()

    fun add(student: String, grade: Int) = roster.add(Pair(student, grade))

    fun grade(grade: Int): List<String> = roster.filter { it.second == grade }.sortAndGetStudents()

    fun roster(): List<String> = roster.sortAndGetStudents()

    private fun List<Pair<String, Int>>.sortAndGetStudents()
            = this.sortedWith( compareBy( {it.second}, {it.first} )).map { it.first }
}