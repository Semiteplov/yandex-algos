package sprint8

// https://contest.yandex.ru/contest/26131/problems/D/
fun findPrefix(strings: Array<String>, minLen: Int, maxLen: Int): Int {
    var prefix = 0

    if (minLen * maxLen == 0) {
        return 0
    }

    if (strings.isEmpty()) {
        return 0
    }

    loop@ for (i in 0 until maxLen) {
        var char: Char? = null
        var match = true

        for (string in strings) {
            if (char == null) {
                if (i < string.length) {
                    char = string[i]
                } else {
                    break@loop
                }
            }

            if (i >= string.length || char != string[i]) {
                match = false
                break
            }
        }

        if (match) {
            prefix++
        } else {
            break
        }
    }

    return prefix
}

fun main() {
    val n = readln().trim().toInt()

    val strings = Array(n) { "" }
    var minLen = Int.MAX_VALUE
    var maxLen = 0

    for (i in 0 until n) {
        strings[i] = readln().trim()
        maxLen = maxOf(maxLen, strings[i].length)
        minLen = minOf(minLen, strings[i].length)
    }

    println(findPrefix(strings, minLen, maxLen))
}
