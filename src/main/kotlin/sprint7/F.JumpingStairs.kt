package sprint7

// https://contest.yandex.ru/contest/25596/problems/F/
fun main() {
    val (n, steps) = readln().split(" ").map { it.toInt() }
    val mod = 1000000007L
    val dp = MutableList(n + 1) { 0L }

    dp[1] = 1L

    for (i in 2..n) {
        for (j in 1..steps) {
            if (i - j >= 1) {
                dp[i] = (dp[i] + dp[i - j]) % mod
            }
        }
    }

    print(dp[n])
}
