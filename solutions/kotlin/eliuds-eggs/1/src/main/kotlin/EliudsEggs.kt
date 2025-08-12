object EliudsEggs {
    fun eggCount(displayNumber: Int): Int {
        var number = displayNumber
        var count = 0

        while (number > 0) {
            if (number % 2 == 1) { // kiểm tra bit cuối
                count++
            }
            number /= 2 // dịch phải 1 bit
        }

        return count
    }
}

