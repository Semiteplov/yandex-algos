package sprint5

// <template>
class NodeA(var value: Int) {
    var left: NodeA? = null
    var right: NodeA? = null
}
// <template>

fun treeSolutionA(head: NodeA?): Int {
    if (head == null) return 0

    val left = treeSolutionA(head.left)
    val right = treeSolutionA(head.right)

    return maxOf(head.value, left, right)
}

fun testA() {
    val NodeA1 = NodeA(1)
    val NodeA2 = NodeA(-5)
    val NodeA3 = NodeA(3)
    NodeA3?.left = NodeA1
    NodeA3?.right = NodeA2
    val NodeA4 = NodeA(2)
    NodeA4?.left = NodeA3
    assert(treeSolutionA(NodeA4) == 3)
}