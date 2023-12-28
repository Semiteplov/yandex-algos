package sprint2

import java.io.BufferedReader
import java.io.InputStreamReader

// https://contest.yandex.ru/contest/22779/problems/I/
class IMyQueueSized(private val maxSize: Int) {
    private val queue = arrayOfNulls<Int>(maxSize)
    private var head = 0
    private var tail = 0
    var size = 0
        private set

    fun peek() = println(queue[head] ?: "None")

    fun push(x: Int) {
        if (size < maxSize) {
            queue[tail] = x
            tail = (tail + 1) % maxSize
            size++
        } else {
            println("error")
        }
    }

    fun pop() {
        if (size > 0) {
            println(queue[head])
            queue[head] = null
            head = (head + 1) % maxSize
            size--
        } else {
            println("None")
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val commands = reader.readLine().toInt()
    val queue = IMyQueueSized(reader.readLine().toInt())

    repeat(commands) {
        val input = reader.readLine().split(" ")
        when (input[0]) {
            "peek" -> queue.peek()
            "push" -> queue.push(input[1].toInt())
            "size" -> println(queue.size)
            "pop" -> queue.pop()
        }
    }
}