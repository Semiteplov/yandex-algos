package sprint1.finals

// https://contest.yandex.ru/contest/22450/run-report/102307496/
fun main() {
    val size = readInt()
    val numbers = readInts()

    require(numbers.size == size) { "Expected $size numbers, but got ${numbers.size}" }
    val result = findClosestZero(numbers)
    println(result.joinToString(" "))
}

private fun findClosestZero(numbers: List<Int>): List<Int> {
    val maxDistance = numbers.size
    val result = MutableList(maxDistance) { maxDistance }

    for (i in numbers.indices) {
        if (numbers[i] == 0) {
            result[i] = 0
        } else if (i > 0) {
            result[i] = result[i - 1] + 1
        }
    }

    for (i in maxDistance - 2 downTo 0) {
        result[i] = minOf(result[i], result[i + 1] + 1)
    }

    return result
}

private fun readInt() = readln().toInt()
private fun readStrings() = readln().split(" ")
private fun readInts() = readStrings().map { it.toInt() }