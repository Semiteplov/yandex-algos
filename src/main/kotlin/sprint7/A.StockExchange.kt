package sprint7

// https://contest.yandex.ru/contest/25596/problems/?nc=Q0hdPHTk

fun main() {
    val n = readln().toInt()
    val prices = readln().split(" ").map { it.toInt() }

    var profit = 0
    for (i in 0 until prices.size - 1) {
        // Если цена на следующий день выше, покупаем сегодня и продаем завтра
        if (prices[i] < prices[i + 1]) {
            profit += prices[i + 1] - prices[i]
        }
    }

    println(profit)
}