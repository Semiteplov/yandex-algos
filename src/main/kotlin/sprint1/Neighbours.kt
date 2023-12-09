package sprint1

data class Matrix(val n: Int, val m: Int, val data: List<IntArray>)

fun main() {
    val matrix = readMatrix()
    val x = readln().toInt()
    val y = readln().toInt()
    val neighbours = getNeighbours(matrix, x, y)
    neighbours.forEach { print("$it ") }
}

private fun getNeighbours(matrix: Matrix, x: Int, y: Int): List<Int> {
    val res = mutableListOf<Int>()

    // Top neighbor
    if (x > 0) res += matrix.data[x - 1][y]

    // Bottom neighbor
    if (x < matrix.n - 1) res += matrix.data[x + 1][y]

    // Left neighbor
    if (y > 0) res += matrix.data[x][y - 1]

    // Right neighbor
    if (y < matrix.m - 1) res += matrix.data[x][y + 1]

    return res
}

fun readMatrix(): Matrix {
    val n = readInt()
    val m = readInt()
    val matrix = buildList(n) {
        for (i in 0 until n) {
            add(readInts().toIntArray())
        }
    }
    return Matrix(n, m, matrix)
}


private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() } // list of ints