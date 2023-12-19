package sprint2

// <template>
class NodeC<V>(var value: V, var next: NodeC<V>?) {}
// <template>

fun solutionC(head: NodeC<String>?, pos: Int): NodeC<String>? {
    if (pos == 0) {
        return head?.next
    }

    var current = head
    var index = 0
    while (index < pos - 1) {
        current = current?.next
        index++
    }

    if (current?.next != null) {
        current.next = current.next?.next
    }

    return head
}

fun testC() {
    val node3 = NodeC("node3", null)
    val node2 = NodeC("node2", node3)
    val node1 = NodeC("node1", node2)
    val node0 = NodeC("node0", node1)
    val newHead = solutionC(node0, 1)
    assert(newHead === node0)
    assert(newHead?.next === node2)
    assert(newHead?.next?.next === node3)
    assert(newHead?.next?.next?.next == null)
    // result is : node0 -> node2 -> node3
}

fun main() {
    testC()
}