package sprint1.finals

// https://contest.yandex.ru/contest/22450/run-report/102307496/
fun main() {
    readInt()
    val numbers = readInts()
    val result = findClosestZero(numbers)
    println(result.joinToString(" "))
}

private fun findClosestZero(numbers: List<Int>): List<Int> {
    val result = MutableList(numbers.size) { Int.MAX_VALUE }

    for (i in numbers.indices) {
        if (numbers[i] == 0) {
            result[i] = 0
        } else if (i > 0 && result[i - 1] != Int.MAX_VALUE) {
            result[i] = result[i - 1] + 1
        }
    }

    for (i in numbers.size - 2 downTo 0) {
        if (result[i + 1] != Int.MAX_VALUE) {
            result[i] = minOf(result[i], result[i + 1] + 1)
        }
    }

    return result
}

private fun readInt() = readln().toInt()
private fun readStrings() = readln().split(" ")
private fun readInts() = readStrings().map { it.toInt() }