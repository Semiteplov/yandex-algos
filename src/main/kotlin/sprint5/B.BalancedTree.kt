package sprint5

import kotlin.math.abs
import kotlin.math.max

// <template>
class NodeB(var value: Int) {
    var left: NodeB? = null
    var right: NodeB? = null
}
// <template>

fun treeSolutionB(head: NodeB?): Boolean {
    return dfs(head) != -1
}

private fun dfs(head: NodeB?): Int {
    if (head == null) return 0

    val leftHeight = dfs(head.left)
    if (leftHeight == -1) return -1

    val rightHeight = dfs(head.right)
    if (rightHeight == -1) return -1

    if (abs(rightHeight - leftHeight) > 1) {
        return -1
    }

    return max(leftHeight, rightHeight) + 1
}

fun testB() {
    val NodeB1 = NodeB(1)
    val NodeB2 = NodeB(-5)
    val NodeB3 = NodeB(3)
    NodeB3?.left = NodeB1
    NodeB3?.right = NodeB2
    val NodeB4 = NodeB(10)
    val NodeB5 = NodeB(2)
    NodeB5?.left = NodeB3
    NodeB5?.right = NodeB4
    assert(treeSolutionB(NodeB5))
}