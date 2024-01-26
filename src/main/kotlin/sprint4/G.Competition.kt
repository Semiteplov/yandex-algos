package sprint4

fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    val arr = if (n != 0) reader.readLine().split(" ").map { it.toInt() } else emptyList()

    var maxLength = 0
    var runningSum = 0
    val sumIndexMap = mutableMapOf<Int, Int>()
    sumIndexMap[0] = -1

    for (i in arr.indices) {
        runningSum += if (arr[i] == 1) 1 else -1

        if (sumIndexMap.containsKey(runningSum)) {
            maxLength = maxOf(maxLength, i - sumIndexMap[runningSum]!!)
        } else {
            sumIndexMap[runningSum] = i
        }
    }

    println(maxLength)
}