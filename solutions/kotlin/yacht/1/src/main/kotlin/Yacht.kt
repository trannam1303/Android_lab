object Yacht {

    fun solve(category: YachtCategory, vararg dices: Int): Int {
        return when (category) {
            YachtCategory.YACHT -> getYacht(dices)
            YachtCategory.FULL_HOUSE -> getFullHouse(dices)
            YachtCategory.FOUR_OF_A_KIND -> getFourOfAKind(dices)
            YachtCategory.LITTLE_STRAIGHT -> getLittleStraight(dices)
            YachtCategory.BIG_STRAIGHT -> getBigStraight(dices)
            YachtCategory.CHOICE -> getChoice(dices)
            else -> getScoreFrom(category, dices)
        }
    }

    private fun getScoreFrom(category: YachtCategory, dices: IntArray): Int {
        val number = when (category) {
            YachtCategory.ONES -> 1
            YachtCategory.TWOS -> 2
            YachtCategory.THREES -> 3
            YachtCategory.FOURS -> 4
            YachtCategory.FIVES -> 5
            else -> 6
        }

        return dices.filter { it == number }.sum()
    }

    private fun getYacht(dices: IntArray) =
            if (dices.toSet().size == 1) 50 else 0

    private fun getFullHouse(dices: IntArray) =
            if (dices.isFullHouse()) dices.sum() else 0

    private fun getFourOfAKind(dices: IntArray): Int {
        val fourDices = dices.sorted().run {
            if (this[0] == this[3]) this.dropLast(1) else this.drop(1)
        }

        return if (fourDices.toSet().size == 1) fourDices.sum() else 0
    }

    private fun getLittleStraight(dices: IntArray) =
            if (dices.sorted().toSet().size == 5 && !dices.contains(6)) 30 else 0

    private fun getBigStraight(dices: IntArray) =
            if (dices.sorted().toSet().size == 5 && !dices.contains(1)) 30 else 0

    private fun getChoice(dices: IntArray) = dices.sum()
}

private fun IntArray.isFullHouse() =
        this.sorted().dropLast(1).toSet().size == 2
                && this.sorted().drop(1).toSet().size == 2