import kotlin.random.Random

class Robot {
    private var _name = ""

    init {
        reset()
    }

    val name: String
        get() = _name

    fun reset() {
        removeName(_name)

        do {
            _name = "${randomLetter()}${randomLetter()}${randomUnder1K()}"
        } while (isNameTaken(_name))

        registerName(_name)
    }
    
    private fun randomLetter() = Random.nextInt(65, 91).toChar()
    private fun randomUnder1K() = Random.nextInt(0, 1000)

    companion object {
        private val names = HashSet<String>()

        fun isNameTaken(name: String): Boolean = names.contains(name)
        fun registerName(name: String) = names.add(name)
        fun removeName(name: String) = names.remove(name)
    }
}