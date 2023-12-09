package sprint1

/**
 * https://contest.yandex.ru/contest/22449/problems/D/
 */
fun main() {
    val n = readInt()
    val arr = readInts()
    val chaosFactor = getChaosFactor(n, arr)
    println(chaosFactor)
}

private fun getChaosFactor(n: Int, arr: List<Int>): Int {
    var res = 0
    if (n == 1) return 1
    for (i in 0 until n) {
        if (i == 0) {
            if (arr[0] > arr[1]) res++
        } else if (i < n - 1 ) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) res++
        } else if (arr[i] > arr[i - 1]) {
            res++
        }
    }
    return res
}

private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() } // list of ints