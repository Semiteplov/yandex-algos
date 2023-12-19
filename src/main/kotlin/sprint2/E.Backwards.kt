package sprint2

// <template>
class NodeE<V>(var value: V) {
    var next: NodeE<V>? = null
    var prev: NodeE<V>? = null

    fun hasNext(): Boolean {
        return next != null
    }

    override fun toString(): String {
        return "value: ${value}, next: ${next}"
    }
}
// <template>


fun solutionE(head: NodeE<String>?): NodeE<String>? {
    var current = head
    while (current?.hasNext() == true) {
        val next = current.next
        current.next = current.prev
        current.prev = next
        current = next
    }
    val next = current?.next
    current?.next = current?.prev
    current?.prev = next
    return current
}


fun testE() {
    val node3 = NodeE("node3")
    val node2 = NodeE("node2")
    val node1 = NodeE("node1")
    val node0 = NodeE("node0")

    node0.next = node1
    node1.next = node2
    node2.next = node3

    node1.prev = node0
    node2.prev = node1
    node3.prev = node2

    val newNode = solutionE(node0)
    println(newNode?.toString())
    println(newNode?.next)
    println(newNode?.prev)
    assert(newNode === node3)
    assert(node3?.next === node2)
    assert(node2?.next === node1)
    assert(node2?.prev === node3)
    assert(node1?.next === node0)
    assert(node1?.prev === node2)
    assert(node0?.prev === node1)
}

fun main() {
    testE()
}