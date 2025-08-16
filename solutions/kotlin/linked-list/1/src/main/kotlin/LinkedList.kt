class Deque<T> {
    private val arr: MutableList<T> = mutableListOf()

    fun push(value: T) {
        arr.add(value)
    }

    fun pop(): T? {
        return arr.removeAt(arr.lastIndex)
    }

    fun unshift(value: T) {
        arr.add(0, value)
    }

    fun shift(): T? {
        return arr.removeAt(arr.indices.first)
    }
}