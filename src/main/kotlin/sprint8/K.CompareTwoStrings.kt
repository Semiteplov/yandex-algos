package sprint8

// https://contest.yandex.ru/contest/26131/problems/K/
fun compare(first: String, second: String): String {
    return when {
        first < second -> "-1"
        first > second -> "1"
        else -> "0"
    }
}

fun main() {
    val alphabet = ('a'..'z').filterIndexed { index, _ -> index % 2 != 0 }.toSet()

    val first = readln().filter { it in alphabet }
    val second = readln().filter { it in alphabet }

    println(compare(first, second))
}
