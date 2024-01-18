package sprint4

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.`out`.bufferedWriter()
    val n = reader.readLine().toInt()
    val sum = reader.readLine().toInt()

    if (n == 0) {
        println(0)
        return
    }

    val arr = reader.readLine().split(" ").map { it.toInt() }

    val result = get4Sum(n, arr, sum)
    writer.write("${result.size}\n")
    result.forEach {
        writer.write("${it.joinToString(" ")}\n")
    }
    writer.flush()
}

fun get4Sum(size: Int, arr: List<Int>, sum: Int): List<List<Int>> {
    val sortedArr = arr.sorted()
    val history = mutableSetOf<Int>()
    val result = mutableSetOf<List<Int>>()

    for (i in 0 until size - 2) {
        for (j in i + 1 until size - 1) {
            val interSum = sum - sortedArr[i] - sortedArr[j]
            for (k in j + 1 until size) {
                val target = interSum - sortedArr[k]
                if (target in history) {
                    result += listOf(target, sortedArr[i], sortedArr[j], sortedArr[k])
                }
            }
        }
        history += sortedArr[i]
    }

    return result.sortedWith(compareBy({ it[0] }, { it[1] }, { it[2] }, { it[3] }))
}

fun get4SumEffective(size: Int, arr: List<Int>, sum: Int): List<List<Int>> {
    val sortedArr = arr.sorted()
    val pairSumMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    val result = mutableSetOf<List<Int>>()

    for (i in 0 until size - 1) {
        for (j in i + 1 until size) {
            val pairSum = sortedArr[i] + sortedArr[j]
            if (pairSumMap[pairSum] == null) {
                pairSumMap[pairSum] = mutableListOf()
            }
            pairSumMap[pairSum]?.add(Pair(i, j))
        }
    }

    for (i in 0 until size - 1) {
        for (j in i + 1 until size) {
            val currentSum = sortedArr[i] + sortedArr[j]
            val complement = sum - currentSum
            pairSumMap[complement]?.forEach { pair ->
                val (k, l) = pair
                if (k > j) {
                    val quadruplet = listOf(sortedArr[i], sortedArr[j], sortedArr[k], sortedArr[l]).sorted()
                    result.add(quadruplet)
                }
            }
        }
    }

    return result.sortedWith(compareBy({ it[0] }, { it[1] }, { it[2] }, { it[3] }))
}

fun get4SumTwoPointers(size: Int, arr: List<Int>, sum: Int): List<List<Int>> {
    val sortedArr = arr.sorted()
    val result = mutableListOf<List<Int>>()

    for (i in 0 until size - 3) {
        if (i > 0 && sortedArr[i] == sortedArr[i - 1]) continue // Skip duplicates

        for (j in i + 1 until size - 2) {
            if (j > i + 1 && sortedArr[j] == sortedArr[j - 1]) continue // Skip duplicates

            var left = j + 1
            var right = size - 1

            while (left < right) {
                // Use Long to prevent integer overflow
                val total: Long = sortedArr[i].toLong() + sortedArr[j] + sortedArr[left] + sortedArr[right]
                when {
                    total == sum.toLong() -> {
                        result.add(listOf(sortedArr[i], sortedArr[j], sortedArr[left], sortedArr[right]))
                        while (left < right && sortedArr[left] == sortedArr[left + 1]) left++ // Skip duplicates
                        while (left < right && sortedArr[right] == sortedArr[right - 1]) right-- // Skip duplicates
                        left++
                        right--
                    }
                    total < sum -> left++
                    else -> right--
                }
            }
        }
    }

    return result
}
