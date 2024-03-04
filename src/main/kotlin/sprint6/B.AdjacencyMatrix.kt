package sprint6

fun main() {
    val reader = System.`in`.bufferedReader()

    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }
    val adjacencyMatrix = MutableList(verticesCount) { MutableList(verticesCount) { 0 } }

    repeat(edgesCount) {
        val (vertex, edge) = reader.readLine().split(" ").map { it.toInt() }
        adjacencyMatrix[vertex - 1][edge - 1] = 1
    }

    for (i in 0 until verticesCount) {
        for (j in 0 until verticesCount) {
            print("${adjacencyMatrix[i][j]} ")
        }
        println()
    }
}