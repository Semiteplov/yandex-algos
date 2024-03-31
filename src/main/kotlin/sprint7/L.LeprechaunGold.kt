package sprint7

// https://contest.yandex.ru/contest/25596/problems/L/
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val bars = readln().split(" ").map { it.toInt() }.sorted()
    val dp = Array(2) { IntArray(m + 1) }

    var idx = 0
    var idxPrev = 1

    for (i in bars.indices) {
        val bar = bars[i]

        for (j in 0..m) {
            dp[idx][j] = dp[idxPrev][j]
        }

        for (j in bar..m) {
            dp[idx][j] = maxOf(dp[idxPrev][j], bar + dp[idxPrev][j - bar])
        }

        idx = idxPrev.also { idxPrev = idx }
    }

    println(dp[idxPrev][m])
}