package sprint6

import java.util.LinkedList

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }

    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
    val distances = MutableList(verticesCount + 1) { -1 }

    repeat(edgesCount) {
        val (vertex, edge) = reader.readLine().split(" ").map { it.toInt() }
        adjacencyList.getOrPut(vertex) { mutableListOf() }.add(edge)
        adjacencyList.getOrPut(edge) { mutableListOf() }.add(vertex)
    }
    val startVertex = reader.readLine().toInt()

    bfs(adjacencyList, distances, startVertex)

    val maxDistance = distances.maxOrNull() ?: -1

    writer.write(maxDistance.toString())
    writer.flush()
}

private fun bfs(
    adjacencyList: MutableMap<Int, MutableList<Int>>,
    distances: MutableList<Int>,
    startVertex: Int,
) {
    val planned = LinkedList<Int>()

    planned.add(startVertex)
    distances[startVertex] = 0

    while (planned.isNotEmpty()) {
        val vertex = planned.poll()

        adjacencyList[vertex]?.let {
            for (v in it) {
                if (distances[v] == -1) {
                    distances[v] = distances[vertex] + 1
                    planned.add(v)
                }
            }
        }
    }
}