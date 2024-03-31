package sprint7

// https://contest.yandex.ru/contest/25596/problems/K/
fun main() {
    val n = readln().toInt()
    val first = readln().split(" ").map { it.toInt() }.take(n)

    val m = readln().toInt()
    val second = readln().split(" ").map { it.toInt() }.take(m)

    val dp = Array(n + 1) { IntArray(m + 1) }

    for (i in 1..n) {
        for (j in 1..m) {
            dp[i][j] = if (first[i - 1] == second[j - 1]) {
                dp[i - 1][j - 1] + 1
            } else {
                maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    if (n == 0 || m == 0) {
        println(0)
    } else {
        val answer = mutableListOf<Int>()
        val lcsFirst = mutableListOf<Int>()
        val lcsSecond = mutableListOf<Int>()

        var i = n
        var j = m

        while (dp[i][j] != 0) {
            when {
                first[i - 1] == second[j - 1] -> {
                    answer.add(first[i - 1])
                    lcsFirst.add(i)
                    lcsSecond.add(j)
                    i -= 1
                    j -= 1
                }

                dp[i][j] == dp[i - 1][j] -> i -= 1
                dp[i][j] == dp[i][j - 1] -> j -= 1
            }
        }

        println(dp[n][m])

        if (dp[n][m] > 0) {
            println(lcsFirst.reversed().joinToString(" "))
            println(lcsSecond.reversed().joinToString(" "))
        }
    }
}
