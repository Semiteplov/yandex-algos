package sprint6

import java.util.LinkedList

private val answer = mutableListOf<Int>()

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }

    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
    val colors = MutableList(verticesCount + 1) { -1 } // white = -1, grey = 0, black = 1

    repeat(edgesCount) {
        val (vertex, edge) = reader.readLine().split(" ").map { it.toInt() }
        adjacencyList.getOrPut(vertex) { mutableListOf() }.add(edge)
        adjacencyList.getOrPut(edge) { mutableListOf() }.add(vertex)
    }
    val startVertex = reader.readLine().toInt()
    adjacencyList.values.forEach {
        it.sort()
    }
    bfs(adjacencyList, colors, startVertex)
    writer.write(answer.joinToString(" "))
    writer.flush()
}

private fun bfs(
    adjacencyList: MutableMap<Int, MutableList<Int>>,
    colors: MutableList<Int>,
    startVertex: Int,
) {
    val planned = LinkedList<Int>()
    planned.add(startVertex)
    colors[startVertex] = 0

    while (planned.isNotEmpty()) {
        val vertex = planned.poll()
        answer.add(vertex)

        adjacencyList[vertex]?.let {
            for (v in it) {
                if (colors[v] == -1) {
                    colors[v] = 0
                    planned.add(v)
                }
            }
        }
        colors[vertex] = 1
    }
}