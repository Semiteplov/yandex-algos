package sprint1

/**
 * https://contest.yandex.ru/contest/22449/problems/E/
 */
fun main() {
    val length = readln()
    val line = readln()
    val biggestWord = calcBiggestWord(line)
    println(biggestWord)
    println(biggestWord.length)
}

private fun calcBiggestWord(line: String): String {
    val words = line.split(" ")
    return words.maxByOrNull { it.length }!!
}