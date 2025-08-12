object Flattener {
    fun flatten(input: List<Any?>): List<Any> {
        val result = mutableListOf<Any>()
        for (item in input) {
            when (item) {
                is List<*> -> result.addAll(flatten(item)) // Đệ quy nếu là danh sách
                null -> {} // Bỏ qua null
                else -> result.add(item) // Thêm phần tử bình thường
            }
        }
        return result
    }
}
