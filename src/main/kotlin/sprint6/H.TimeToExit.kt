package sprint6

var time = -1

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }

    val entries = MutableList<Int?>(verticesCount + 1) { null }
    val exits = MutableList<Int?>(verticesCount + 1) { null }
    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()

    repeat(edgesCount) {
        val (vertex, edge) = reader.readLine().split(" ").map { it.toInt() }
        adjacencyList.getOrPut(vertex) { mutableListOf() }.add(edge)
    }
    adjacencyList.forEach { (_, neighbors) ->
        neighbors.sort()
    }

    val startVertex = 1
    val visited = MutableList(verticesCount + 1) { false }

    dfs(adjacencyList, visited, startVertex, entries, exits)

    for (i in 1 until verticesCount + 1) {
        writer.write("${entries[i]} ${exits[i]}\n")
    }

    writer.flush()
}

private fun dfs(
    adjacencyList: MutableMap<Int, MutableList<Int>>,
    visited: MutableList<Boolean>,
    startVertex: Int,
    entries: MutableList<Int?>,
    exits: MutableList<Int?>,
) {
    visited[startVertex] = true
    time++
    entries[startVertex] = time

    adjacencyList[startVertex]?.forEach { neighbor ->
        if (!visited[neighbor]) dfs(adjacencyList, visited, neighbor, entries, exits)
    }

    time++
    exits[startVertex] = time
}