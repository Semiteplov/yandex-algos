package sprint4

// https://contest.yandex.ru/contest/23991/problems/C/
fun main() {
    val reader = System.`in`.bufferedReader()
    val a = reader.readLine().toLong()
    val m = reader.readLine().toLong()
    val s = reader.readLine()
    val t = reader.readLine().toInt()
    val prefixHashes = LongArray(s.length + 1)

    for (i in s.indices) {
        prefixHashes[i + 1] = (prefixHashes[i] * a + s[i].code.toLong()) % m
    }

    val powers = LongArray(s.length + 1)
    powers[0] = 1
    for (i in 1 until powers.size) {
        powers[i] = (powers[i - 1] * a) % m
    }

    repeat(t) {
        val (l, r) = reader.readLine().split(' ').map { it.toInt() }
        val hash = (prefixHashes[r] - prefixHashes[l - 1] * powers[r - l + 1] + m * m) % m
        println(hash)
    }
}