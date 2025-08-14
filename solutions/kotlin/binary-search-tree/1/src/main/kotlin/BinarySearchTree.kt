class BinarySearchTree<T : Comparable<T>> {

    data class Node<T>(val data: T, var left: Node<T>?, var right: Node<T>?)

    var root: Node<T>? = null

    fun insert(value: T) {
        root = insertHelper(value, root)
    }

    private fun insertHelper(value: T, node: Node<T>?): Node<T> {
        when {
            node == null -> return Node(value, null, null)
            value > node.data -> node.right = insertHelper(value, node.right)
            else -> node.left = insertHelper(value, node.left)
        }
        return node
    }

    fun asSortedList(): List<T> {
        return sortedHelper(root!!)
    }

    private fun sortedHelper(node: Node<T>?): List<T> {
        return if (node == null) {
            emptyList()
        } else {
            sortedHelper(node.left) + listOf(node.data) + sortedHelper(node.right)
        }
    }

    fun asLevelOrderList(): List<T> {
        val nodes = mutableListOf(root)
        var i = 0
        while (i < nodes.size) {
            if (nodes[i]?.left != null) nodes.add(nodes[i]?.left)
            if (nodes[i]?.right != null) nodes.add(nodes[i]?.right)
            i++
        }
        return nodes.filterNotNull().map { it.data }
    }
}