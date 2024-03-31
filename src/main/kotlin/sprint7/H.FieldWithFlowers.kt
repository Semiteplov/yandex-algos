package sprint7

// https://contest.yandex.ru/contest/25596/problems/H/
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    // Добавляем дополнительную строку и столбец к points для упрощения граничных условий
    val points = Array(n + 1) { IntArray(m + 1) }

    for (i in 0 until n) {
        readln().map { it - '0' }.forEachIndexed { index, value -> points[i][index + 1] = value }
    }
    val dp = Array(n + 1) { IntArray(m + 1) } // Инициализируем dp массив

    // Переход динамики
    for (i in n - 1 downTo 0) {
        for (j in 1..m) {
            dp[i][j] = maxOf(dp[i][j - 1], dp[i + 1][j]) + points[i][j]
        }
    }
    // Выводим ответ, который находится в dp[0][m]
    println(dp[0][m])
}
