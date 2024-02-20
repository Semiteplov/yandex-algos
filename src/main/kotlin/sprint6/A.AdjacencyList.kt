package sprint6

fun main() {
    val reader = System.`in`.bufferedReader()

    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }
    val adjacencyList = mutableMapOf<Int, List<Int>>()

    repeat(edgesCount) {
        val (vertex, edge)  = reader.readLine().split(" ").map { it.toInt() }
        adjacencyList[vertex] = adjacencyList.getOrDefault(vertex, emptyList()) + edge
    }

    for (vertex in 1..verticesCount) {
        val edgesPerVertex = adjacencyList.getOrDefault(vertex, emptyList())
        println("${edgesPerVertex.size} ${edgesPerVertex.sorted().joinToString(" ")}")
    }
}