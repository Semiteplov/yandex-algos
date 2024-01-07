package sprint3

// https://contest.yandex.ru/contest/23638/problems/H
fun main() {
    val size = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }.toMutableList()

    for (i in 1 until size) {
        val current = arr[i]
        var j = i

        while (j > 0 && (current.toString() + arr[j - 1].toString() > arr[j - 1].toString() + current.toString())) {
            arr[j] = arr[j - 1]
            j--
        }

        arr[j] = current
    }

    println(arr.joinToString(""))
}
