package sprint1

// https://contest.yandex.ru/contest/22449/problems/F/
fun main() {
    val line = readln()
    println(if (isPalindrome(line)) "True" else "False")
}

private fun isPalindrome(line: String): Boolean {
    val filteredS = line.filter { it.isLetterOrDigit() }.lowercase()
    var left = 0
    var right = filteredS.length - 1

    while (left < right) {
        if (filteredS[left] == filteredS[right]) {
            left++
            right--
        } else {
            return false
        }
    }
    return true
}