package sprint2

// https://contest.yandex.ru/contest/22779/problems/G/
class StackMaxEffective {
    private val mainStack = mutableListOf<Int>()
    private val maxStack = mutableListOf<Int>()

    fun push(x: Int) {
        mainStack += x
        if (maxStack.isEmpty() || x >= maxStack.last()) {
            maxStack += x
        }
    }

    fun pop() {
        if (mainStack.isEmpty()) {
            println("error")
            return
        }
        val top = mainStack.removeLast()
        if (top == maxStack.last()) {
            maxStack.removeLast()
        }
    }

    fun get_max() {
        if (maxStack.isEmpty()) {
            println("None")
        } else {
            println(maxStack.last())
        }
    }

    fun top() {
        if (mainStack.isEmpty()) {
            println("error")
            return
        }
        println(mainStack.last())
    }
}

fun main() {
    val stack = StackMaxEffective()
    val n = readlnOrNull()?.toIntOrNull() ?: 0

    repeat(n) {
        val input = readlnOrNull()?.split(" ")
        when (input?.first()) {
            "push" -> stack.push(input[1].toInt())
            "pop" -> stack.pop()
            "get_max" -> stack.get_max()
            "top" -> stack.top()
        }
    }
}