package sprint3

// https://contest.yandex.ru/contest/23638/problems/N/
fun main() {
    val n = readln().toInt()
    val arr = mutableListOf<Array<Int>>()
    repeat(n) {
        val (start, end) = readln().split(" ").map(String::toInt)
        arr.add(arrayOf(start, end))
    }

    arr.sortBy { it.first() }

    var current = arr[0]
    val result = mutableListOf<Array<Int>>()
    for (i in 1 until arr.size) {
        if (current[1] >= arr[i][0]) {
            current[1] = maxOf(current[1], arr[i][1])
        } else {
            result.add(current)
            current = arr[i]
        }
    }
    result.add(current)

    result.forEach { println("${it[0]} ${it[1]}") }
}