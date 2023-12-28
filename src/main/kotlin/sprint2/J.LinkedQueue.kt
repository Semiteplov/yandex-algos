package sprint2

import java.io.BufferedReader
import java.io.InputStreamReader

// https://contest.yandex.ru/contest/22779/problems/J/
class NodeJ<V>(
    var value: V,
    var next: NodeJ<V>? = null,
)

class MyQueueSized() {
    private var size = 0
    private var current: NodeJ<Int>? = null
    private var head: NodeJ<Int>? = null

    fun size() {
        println(size)
    }

    fun put(x: Int) {
        if (head == null) {
            head = NodeJ(x)
            current = head
        } else {
            current?.next = NodeJ(x)
            current = current?.next
        }
        size++
    }

    fun get() {
        if (head == null) {
            println("error")
        } else {
            println(head!!.value)
            head = head!!.next
            size--
        }
    }
}

fun main() {
    val queue = MyQueueSized()
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val commands = reader.readLine().toInt()
    repeat(commands) {
        val input = reader.readLine().split(" ")
        when(input[0]) {
            "get" -> queue.get()
            "size" -> queue.size()
            "put" -> queue.put(input[1].toInt())
        }
    }
}