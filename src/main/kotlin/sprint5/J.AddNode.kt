package sprint5

// <template>
class NodeJ(var left: NodeJ?, var right: NodeJ?, var value: Int)
// <template>

fun insert(root: NodeJ, key: Int): NodeJ {
    if (root == null) return NodeJ(null, null, key)

    if (key <= root.value) {
        root.right = insert(root.right!!, key)
    } else {
        root.left = insert(root.left!!, key)
    }

    return root
}

fun testJ() {
    val NodeJ1 = NodeJ(null, null, 7)
    val NodeJ2 = NodeJ(NodeJ1, null, 8)
    val NodeJ3 = NodeJ(null, NodeJ2, 7)
    val newHead = insert(NodeJ3, 6)
    assert(newHead == NodeJ3)
    assert(newHead?.left!!.value == 6)
}