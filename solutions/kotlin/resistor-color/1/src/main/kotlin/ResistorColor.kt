object ResistorColor {
    private val colors = listOf(
        "black", "brown", "red", "orange", "yellow",
        "green", "blue", "violet", "grey", "white"
    )

    // Lấy giá trị số từ tên màu
    fun colorCode(color: String): Int {
        return colors.indexOf(color.lowercase())
    }

    // Trả về danh sách tất cả các màu
    fun colors(): List<String> {
        return colors
    }
}

