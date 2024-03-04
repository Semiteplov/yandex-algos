package sprint6

private var componentCount = 0

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }

    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
    val colors = MutableList(verticesCount + 1) { -1 }

    repeat(edgesCount) {
        val (vertex, edge) = reader.readLine().split(" ").map { it.toInt() }
        adjacencyList.getOrPut(vertex) { mutableListOf() }.add(edge)
        adjacencyList.getOrPut(edge) { mutableListOf() }.add(vertex)
    }

    for (i in 1 until verticesCount + 1) {
          if (colors[i] == -1) {
              dfs(adjacencyList, colors, i)
              componentCount++
          }
    }

    writer.write("$componentCount\n")

    val components = MutableList(componentCount) { mutableListOf<Int>() }

    for (i in 1 until verticesCount + 1) {
        if (colors[i] != -1) {
            components[colors[i]] += i
        }
    }
    components.forEach { component ->
        writer.write(component.sorted().joinToString(" ") + "\n")
    }

    writer.flush()
}

private fun dfs(
    adjacencyList: MutableMap<Int, MutableList<Int>>,
    colors: MutableList<Int>,
    startVertex: Int,
) {
    colors[startVertex] = componentCount

    adjacencyList[startVertex]?.forEach { neighbor ->
        if (colors[neighbor] == -1) dfs(adjacencyList, colors, neighbor)
    }
}