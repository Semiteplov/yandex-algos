package sprint8

// https://contest.yandex.ru/contest/26131/problems/L/
fun prefixFunction(s: String): List<Int> {
    val prefix = MutableList(s.length) { 0 }
    var j = 0
    var i = 1

    while (i < s.length) {
        if (s[i] != s[j]) {
            if (j > 0) {
                j = prefix[j - 1]
            } else {
                i += 1
            }
        } else {
            prefix[i] = j + 1
            i += 1
            j += 1
        }
    }

    return prefix
}

fun main() {
    val string = readln().trim()
    println(prefixFunction(string).joinToString(" "))
}
