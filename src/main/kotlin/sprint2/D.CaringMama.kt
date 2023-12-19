package sprint2

// <template>
class NodeD<V>(var value: V, var next: NodeD<V>?) {}
// <template>

fun solutionD(head: NodeD<String>?, item: String): Int {
    var current = head
    var i = 0
    while (current?.next !== null) {
        if (current.value == item) {
            return i
        }
        current = current.next
        i++
    }
    return -1
}

fun testD() {
    val node3 = NodeD("node3", null)
    val node2 = NodeD("node2", node3)
    val node1 = NodeD("node1", node2)
    val node0 = NodeD("node0", node1)
    val idx: Int = solutionD(node0, "node2")
    assert(idx == 2)
}

fun main() {
    testD()
}