package sprint8

// https://contest.yandex.ru/contest/26131/problems/E/
fun insertStrings(s: String, inserts: List<Pair<String, Int>>): String {
    val result = StringBuilder(s)
    for ((str, index) in inserts.sortedByDescending { it.second }) {
        result.insert(index, str)
    }
    return result.toString()
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val s = reader.readLine()
    val n = reader.readLine().toInt()
    val inserts = mutableListOf<Pair<String, Int>>()
    repeat(n) {
        val (str, index) = reader.readLine().split(" ")
        inserts.add(str to index.toInt())
    }
    val result = insertStrings(s, inserts)
    println(result)
}