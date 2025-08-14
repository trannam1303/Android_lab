import kotlin.random.Random

class DndCharacter {

    val strength: Int = ability()  // Wywołanie funkcji ability() dla siły
    val dexterity: Int = ability() // Wywołanie funkcji ability() dla zręczności
    val constitution: Int = ability() // Wywołanie funkcji ability() dla kondycji
    val intelligence: Int = ability() // Wywołanie funkcji ability() dla inteligencji
    val wisdom: Int = ability() // Wywołanie funkcji ability() dla mądrości
    val charisma: Int = ability() // Wywołanie funkcji ability() dla charyzmy
    val hitpoints: Int = 10 + modifier(constitution) // Obliczanie punktów życia na podstawie modyfikatora konstytucji

    companion object {

        // Funkcja, która generuje wynik dla jednej zdolności postaci
        fun ability(): Int {
            // Tworzymy listę czterech rzutów kośćmi 6-ściennymi
            val rolls = List(4) { Random.nextInt(1, 7) }
            // Sortujemy je malejąco i bierzemy sumę trzech najwyższych
            val sortedRolls = rolls.sortedDescending()
            return sortedRolls.take(3).sum()
        }

        // Funkcja, która oblicza modyfikator na podstawie wyniku dla konstytucji
        fun modifier(score: Int): Int {
            // Zmieniamy obliczenia, aby zaokrąglać w dół
            return (score - 10).floorDiv(2)
        }
    }
}




