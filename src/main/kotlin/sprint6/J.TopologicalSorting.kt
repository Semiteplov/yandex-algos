package sprint6

import java.util.*

val stack = Stack<Int>()

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }
    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()

    repeat(edgesCount) {
        val (vertex, edge)  = reader.readLine().split(" ").map { it.toInt() }
        adjacencyList.getOrPut(vertex) { mutableListOf() }.add(edge)
    }

    val visited = MutableList(verticesCount + 1) { false }

    for (vertex in 1 until verticesCount + 1) { // учитываем несвязные графы
        if (!visited[vertex]) {
            dfs(adjacencyList, visited, vertex)
        }
    }

    stack.reversed().forEach { writer.write("$it ") }

    writer.flush()
}

private fun dfs(adjacencyList: MutableMap<Int, MutableList<Int>>, visited: MutableList<Boolean>, startVertex: Int) {
    visited[startVertex] = true

    adjacencyList[startVertex]?.forEach { neighbor ->
        if (!visited[neighbor]) dfs(adjacencyList, visited, neighbor)
    }
    stack.push(startVertex)
}