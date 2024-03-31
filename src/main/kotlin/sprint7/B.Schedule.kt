package sprint7

// https://contest.yandex.ru/contest/25596/problems/B/

data class Lesson(val start: String, val end: String)

fun main() {
    val n = readln().toInt()
    val lessons = mutableListOf<Lesson>()
    val result = mutableListOf<Lesson>()

    repeat(n) {
        val (start, end) = readln().split(" ")
        lessons += Lesson(start, end)
    }

    val comparator = compareBy<Lesson> { it.end.toDouble() }.thenBy { it.start.toDouble() }
    lessons.sortWith(comparator)

    result += lessons[0]
    for (i in 1 until n) {
        if (lessons[i].start.toDouble() >= result.last().end.toDouble()) {
            result += lessons[i]
        }
    }

    println(result.size)
    result.forEach {
        println("${it.start} ${it.end}")
    }
}