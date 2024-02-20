package sprint6

import java.io.BufferedWriter

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }
    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()

    repeat(edgesCount) {
        val (vertex, edge)  = reader.readLine().split(" ").map { it.toInt() }
        adjacencyList.getOrPut(vertex) { mutableListOf() }.add(edge)
        adjacencyList.getOrPut(edge) { mutableListOf() }.add(vertex) // Для неориентированного графа добавляем связь в обратную сторону
    }
    adjacencyList.forEach { (_, neighbors) ->
        neighbors.sort()
    }

    val startVertex = reader.readLine().toInt()
    val visited = MutableList(verticesCount + 1) { false }

    dfs(adjacencyList, visited, startVertex, writer)
    writer.flush()
}

private fun dfs(adjacencyList: MutableMap<Int, MutableList<Int>>, visited: MutableList<Boolean>, startVertex: Int, writer: BufferedWriter) {
    visited[startVertex] = true
    writer.write("$startVertex ")

    adjacencyList[startVertex]?.forEach { neighbor ->
        if (!visited[neighbor]) dfs(adjacencyList, visited, neighbor, writer)
    }

}