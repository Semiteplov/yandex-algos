package sprint1

// https://contest.yandex.ru/contest/22449/problems/G/
fun main() {
    val number = readInt()
    println(toBinary(number))
}

private fun toBinary(number: Int): String {
    val res = mutableListOf<Int>()
    var n = number
    if (n == 0) return "0"
    while (n != 0) {
        res.add(0, n % 2)
        n /= 2
    }
    return res.joinToString("")
}

private fun readStr() = readln()
private fun readInt() = readStr().toInt()