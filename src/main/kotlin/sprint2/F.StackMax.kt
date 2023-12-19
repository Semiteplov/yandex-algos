package sprint2

// https://contest.yandex.ru/contest/22779/problems/F/
class StackMax() {
    private val stack = mutableListOf<Int>()

    fun pop(): String {
        return if (stack.isEmpty()) "error\n" else {
            stack.removeAt(stack.size - 1)
            return ""
        }
    }

    fun push(x: Int) {
        stack += x
    }

    fun get_max(): String {
        return if (stack.isEmpty()) "None" else stack.max().toString()
    }
}

fun main() {
    val stack = StackMax()
    var res = ""

    val commands = readln().toInt()
    repeat(commands) {
        val command = readln().split(" ")
        when (command[0]) {
            "get_max" -> res += stack.get_max().plus("\n")
            "push" -> stack.push(command[1].toInt())
            "pop" -> res += stack.pop()
        }

        print(res)
    }
}