package sprint4

// https://contest.yandex.ru/contest/23991/problems/A/
fun main() {
    val a = readln().toInt()
    val m = readln().toInt()
    val str = readln()
    if (str.isEmpty()) println(0) else print(polynomialHash(str, a, m))
}

fun polynomialHash(str: String, a: Int, m: Int): Long {
    var res = 0L
    for (i in 0 until str.length - 1) {
        res = (res + str[i].code) * a % m
        println(res)
    }
    res += str.last().code
    return res % m
}