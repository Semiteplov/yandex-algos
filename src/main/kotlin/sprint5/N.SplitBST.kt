package sprint5

// <template>
class NodeN(var left: NodeN?, var right: NodeN?, var value: Int, var size: Int)
// <template>

fun split(root: NodeN?, k: Int): List<NodeN?> {
    if (root == null) return listOf(null, null)

    // Проверяем размер левого поддерева
    if (getSize(root.left) + 1 <= k) {
        // Спускаемся в правое поддерево
        val kRemaining = k - getSize(root.left) - 1
        val splitRight = split(root.right, kRemaining)
        root.right = splitRight[0]
        updateSize(root)
        return listOf(root, splitRight[1])
    }

    // Спускаемся в левое поддерево
    val splitLeft = split(root.left, k)
    root.left = splitLeft[1]
    updateSize(root)
    return listOf(splitLeft[0], root)
}

fun updateSize(NodeN: NodeN) {
    NodeN.size = getSize(NodeN.left) + getSize(NodeN.right) + 1
}

fun getSize(NodeN: NodeN?): Int {
    return NodeN?.size ?: 0
}

fun testN() {
    val NodeN1 = NodeN(null, null, 3, 1)
    val NodeN2 = NodeN(null, NodeN1, 2, 2)
    val NodeN3 = NodeN(null, null, 8, 1)
    val NodeN4 = NodeN(null, null, 11, 1)
    val NodeN5 = NodeN(NodeN3, NodeN4, 10, 3)
    val NodeN6 = NodeN(NodeN2, NodeN5, 5, 6)
    val res: List<NodeN?> = split(NodeN6, 4)
    assert(res[0]!!.size == 4)
    assert(res[1]!!.size == 2)
}

fun main() {
    testN()
}