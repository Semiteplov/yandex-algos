package sprint2

// https://contest.yandex.ru/contest/22779/problems/B/
// <template>
class NodeB<V>(
    var value: V,
    var next: NodeB<V>? = null,
)
// <template>

fun solutionA(head: NodeB<String>?) {
    var current = head
    while (current?.next != null) {
        println(current.value)
        current = current.next
    }
    println(current?.value)
}

fun testB() {
    val node3 = NodeB("node3")
    val node2 = NodeB("node2", node3)
    val node1 = NodeB("node1", node2)
    val node0 = NodeB("node0", node1)
    solutionA(node0)
    /*
    Output is:
    node0
    node1
    node2
    node3
    */
}

fun main() {
    testB()
}