package sprint3

// https://contest.yandex.ru/contest/23638/problems/K/
fun merge(arr: IntArray, left: Int, mid: Int, right: Int): IntArray {
    val result = mutableListOf<Int>()
    var l = left
    var r = mid
    while (l < mid && r < right) {
        if (arr[l] <= arr[r]) {
            result.add(arr[l])
            l++
        } else {
            result.add(arr[r])
            r++
        }
    }

    while (l < mid) {
        result.add(arr[l])
        l++
    }
    while (r < right) {
        result.add(arr[r])
        r++
    }

    return result.toIntArray()
}

fun merge_sort(arr: IntArray, left: Int, right: Int) {
   if (left < right - 1) {
       val mid = (left + right) / 2
       merge_sort(arr, left, mid)
       merge_sort(arr, mid, right)
       val merged = merge(arr, left, mid, right)
       for (i in merged.indices) {
           arr[i + left] = merged[i]
       }
   }
}

fun test() {
    val a = intArrayOf(1, 4, 9, 2, 10, 11)
    val b: IntArray = merge(a, 0, 3, 6)
    val expected = intArrayOf(1, 2, 4, 9, 10, 11)
    assert(b.contentEquals(expected))
    val c = intArrayOf(1, 4, 2, 10, 1, 2)
    merge_sort(c, 0, 6)
    val expected2 = intArrayOf(1, 1, 2, 2, 4, 10)
    assert(c.contentEquals(expected2))
}
