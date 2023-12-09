package sprint1

import kotlin.math.abs

fun main() {
    val (a, b, c) = readInts()
    val ok = solve(a, b, c)
    if (ok) {
        println("WIN")
    } else {
        println("FAIL")
    }
}

private fun solve(a: Int, b: Int, c: Int): Boolean {
    val a1 = abs( a % 2)
    val b1 = abs(b % 2)
    val c1 = abs(c % 2)
    return (a1 == b1) && (b1 == c1) && (a1 == c1)
}

private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }