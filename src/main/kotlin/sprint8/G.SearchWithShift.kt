package sprint8

// https://contest.yandex.ru/contest/26131/problems/G/
fun main() {
    val n = readln().toInt()  // Number of measurements
    val x = readln().split(" ").map { it.toInt() }  // Measurement results
    val m = readln().toInt()  // Length of the pattern
    val a = readln().split(" ").map { it.toInt() }  // Elements of the pattern

    val positions = mutableListOf<Int>()

    for (i in 0..n - m) {
        val c = a[0] - x[i]  // Calculate the constant shift
        var match = true

        for (j in 0 until m) {
            if (x[i + j] != a[j] - c) {  // Check if the shifted subsequence matches the pattern
                match = false
                break
            }
        }

        if (match) {
            positions.add(i + 1)  // Store positions in 1-based index
        }
    }

    println(positions.joinToString(" "))  // Output the positions
}
