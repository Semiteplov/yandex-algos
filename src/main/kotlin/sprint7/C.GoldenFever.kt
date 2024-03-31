package sprint7

// https://contest.yandex.ru/contest/25596/problems/C/

data class PileOfGold(val weight: Int, val price: Int)

fun main() {
    var capacity = readln().toInt()
    val pilesCount = readln().toInt()
    val piles = mutableListOf<PileOfGold>()

    repeat(pilesCount) {
        val (price, weight) = readln().split(" ").map { it.toInt() }
        piles += PileOfGold(weight, price)
    }
    piles.sortWith(compareByDescending { it.price })

    var sum: Long = 0

    for (pile in piles) {
        if (pile.weight <= capacity) {
            capacity -= pile.weight
            sum += pile.price.toLong() * pile.weight
        } else {
            sum += pile.price.toLong() * capacity
            break
        }
    }

    println(sum)
}
