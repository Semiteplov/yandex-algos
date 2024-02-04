package sprint5

// <template>
class NodeE {
    var value: Int
    var left: NodeE?
    var right: NodeE?

    constructor(value: Int) {
        this.value = value
        right = null
        left = null
    }

    constructor(value: Int, left: NodeE?, right: NodeE?) {
        this.value = value
        this.left = left
        this.right = right
    }
}
// <template>

fun treeSolutionE(head: NodeE?): Boolean {
    fun isBST(node: NodeE?, min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Boolean {
        if (node == null) return true
        if (node.value <= min || node.value >= max) return false
        return isBST(node.left, min, node.value) && isBST(node.right, node.value, max)
    }

    return isBST(head)
}


fun testE() {
    val NodeE1 = NodeE(1, null, null)
    val NodeE2 = NodeE(4, null, null)
    val NodeE3 = NodeE(3, NodeE1, NodeE2)
    val NodeE4 = NodeE(8, null, null)
    val NodeE5 = NodeE(5, NodeE3, NodeE4)
    assert(treeSolutionE(NodeE5))
    NodeE2.value = 5
    assert(!treeSolutionE(NodeE5))
}