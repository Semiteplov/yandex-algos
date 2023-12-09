package sprint1

// https://contest.yandex.ru/contest/22449/problems/I/
fun main() {
    val number = readInt()
    println(if (isDegreeOfFour(number)) "True" else "False")
}

private fun isDegreeOfFour(n: Int): Boolean {
    var num = n
    var m = n
    if (num == 1) return true
    while (num >= 4) {
        m = num % 4
        num /= 4
        if (m != 0) {
            return false
        }
    }
    return m == 0 && num == 1
}

private fun readStr() = readln()
private fun readInt() = readStr().toInt()