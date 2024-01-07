package sprint3

fun main() {
    val size = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }.toMutableList()

    var swap: Boolean
    var isFirstIteration = true

    for (i in 0 until size) {
        swap = false

        for (j in 1 until size) {
            if (arr[j] < arr[j - 1]) {
                val temp = arr[j]
                arr[j] = arr[j - 1]
                arr[j - 1] = temp
                swap = true
            }
        }

        if (!swap) {
            if (isFirstIteration) {
                println(arr.joinToString(" "))
            }
            break
        } else {
            println(arr.joinToString(" "))
        }
        isFirstIteration = false
    }
}
