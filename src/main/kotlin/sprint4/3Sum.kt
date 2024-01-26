package sprint4

fun effectiveSum3(arr: List<Int>, sum: Int): Set<List<Int>> {
    val history = mutableSetOf<Int>()
    val sorted = arr.sorted()
    val result = mutableSetOf<List<Int>>()

    for (i in arr.indices) {
        for (j in (i + 1) until arr.size) {
            val target = sum - sorted[j] - sorted[i]
            if (target in history) {
                result += listOf(target, sorted[i], sorted[j])
            }
        }
        history.add(sorted[i])
    }

    return result
}

fun twoPointersSum3(arr: List<Int>, sum: Int): Set<List<Int>> {
    val sorted = arr.sorted()
    val result = mutableSetOf<List<Int>>()

    for (i in arr.indices) {
        // Пропускаем дубликаты для первого элемента тройки
        if (i > 0 && sorted[i] == sorted[i - 1]) {
            continue
        }

        var left = i + 1
        var right = arr.size - 1

        while (left < right) {
            val currentSum = sorted[i] + sorted[left] + sorted[right]
            if (currentSum == sum) {
                result += listOf(sorted[i], sorted[left], sorted[right])

                // Пропускаем дубликаты для второго и третьего элементов тройки
                while (left < right && sorted[left] == sorted[left + 1]) left++
                while (left < right && sorted[right] == sorted[right - 1]) right--

                left++
                right--
            } else if (currentSum < sum) {
                left++
            } else {
                right--
            }
        }
    }

    return result
}

fun main() {
    val effectiveSum = effectiveSum3(listOf(3, 1, 5, 4, 3, 8, 2, 5, 2, 9, 0, 4, 1, 0, 9), 4)
    println("effectiveSum: $effectiveSum")

    val twoPointerSum = twoPointersSum3(listOf(3, 1, 5, 4, 3, 8, 2, 5, 2, 9, 0, 4, 1, 0, 9), 4)
    println("twoPointerSum: $twoPointerSum")
}