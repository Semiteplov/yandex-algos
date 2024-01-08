package sprint3

// https://contest.yandex.ru/contest/23638/problems/
/*
 onopen
 onopen
 onclose
 onclose
 (())
 onclose
 onopen
 onclose
 ()()
 */
fun main() {
    val number = readln().toInt()
    generateBrackets(number * 2, "", 0, 0)
}

fun generateBrackets(number: Int, prefix: String, open: Int, close: Int) {
    if (prefix.length == number) {
        println(prefix)
        return
    }

    if (open < number / 2) {
        println("onopen")
        generateBrackets(number, "$prefix(", open + 1, close)
    }

    if (open > close) {
        println("onclose")
        generateBrackets(number, "$prefix)", open, close + 1)
    }
}