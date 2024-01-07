package sprint3

// https://contest.yandex.ru/contest/23638/problems/G
fun main() {
    val reader = System.`in`.bufferedReader()
    val n = reader.readLine().toInt()
    if (n == 0) return
    val arr = reader.readLine().split(" ").map { it.toInt() }.toIntArray()
    val result = countingSort(arr, 3)
    result.forEach { print("$it ") }
}

fun countingSort(arr: IntArray, n: Int): IntArray {
    val countedValues = IntArray(n) { 0 }
    for (i in arr) {
        countedValues[i]++
    }

    var index = 0
    for (i in 0 until n) {
        for (amount in 1 until countedValues[i] + 1) {
            arr[index] = i
            index++
        }
    }
    return arr
}

//fun sortColors(clothes: IntArray) {
//    var low = 0
//    var mid = 0
//    var high = clothes.size - 1
//    var temp: Int
//
//    while (mid <= high) {
//        when (clothes[mid]) {
//            0 -> { // for pink color
//                temp = clothes[low]
//                clothes[low] = clothes[mid]
//                clothes[mid] = temp
//                low++
//                mid++
//            }
//            1 -> { // for yellow color
//                mid++
//            }
//            2 -> { // for raspberry color
//                temp = clothes[mid]
//                clothes[mid] = clothes[high]
//                clothes[high] = temp
//                high--
//            }
//        }
//    }
//}
//
//fun main() {
//    val n = readLine()!!.toInt()  // Reading the number of items
//    if (n == 0) return
//    val clothes = readLine()!!.split(' ').map { it.toInt() }.toIntArray()  // Reading the array of colors
//
//    sortColors(clothes)
//
//    println(clothes.joinToString(separator = " "))
//}