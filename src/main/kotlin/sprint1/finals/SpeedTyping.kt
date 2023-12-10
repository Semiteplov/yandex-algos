package sprint1.finals

import java.util.HashMap

private const val MATRIX_SIZE = 4
private const val PLAYERS_NUMBER = 2

// https://contest.yandex.ru/contest/22450/run-report/102332370/
fun main() {
    val keysNumber = readInt()
    val matrix = readMatrix()
    val score = getScore(keysNumber, matrix)
    println(score)
}

private fun getScore(k: Int, matrix: List<List<Int>>): Int {
    val map = HashMap<Int, Int>()

    for (rows in matrix) {
        for (element in rows) {
            map.merge(element, 1) { oldValue, newValue -> oldValue + newValue }
        }
    }

    return map.values.count { it <= k * PLAYERS_NUMBER }
}

private fun readMatrix(): List<List<Int>> {
    return (0 until MATRIX_SIZE).map {
        readString().mapNotNull { char ->
            if (char != '.') char.toString().toInt() else null
        }
    }
}

private fun readString() = readln()
private fun readInt() = readString().toInt()

