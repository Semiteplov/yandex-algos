package sprint6

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val (landmarksCount, bridgesCount) = reader.readLine().split(" ").map { it.toInt() }
    val adjacencyList = List(landmarksCount + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(bridgesCount) {
        val (u, v, weight) = reader.readLine().split(" ").map { it.toInt() }
        adjacencyList[u] += Pair(v, weight)
        adjacencyList[v] += Pair(u, weight)
    }

    val distances = Array(landmarksCount + 1) { IntArray(landmarksCount + 1) { Int.MAX_VALUE } }

    for (i in 1 until landmarksCount + 1) {
        dijkstra(adjacencyList, distances, i, landmarksCount)
    }

    for (i in 1 until landmarksCount + 1) {
        for (j in 1 until landmarksCount + 1) {
            if (distances[i][j] == Int.MAX_VALUE || distances[i][j] < 0) {
                writer.write("-1 ")
            } else {
                writer.write("${distances[i][j]} ")
            }
        }
        writer.write("\n")
    }

    writer.write("\n")
    writer.flush()
}

fun dijkstra(
    adjacencyList: List<MutableList<Pair<Int, Int>>>,
    distances: Array<IntArray>,
    start: Int,
    landmarksCount: Int
) {
    val minDistances = IntArray(landmarksCount + 1) { Int.MAX_VALUE }.apply { this[start] = 0 }
    val visited = BooleanArray(landmarksCount + 1)

    for (i in 1 until landmarksCount + 1) {
        val u = minDistances.withIndex().filter { !visited[it.index] && it.index != 0 }.minByOrNull { it.value }?.index
            ?: continue
        visited[u] = true
        for ((v, l) in adjacencyList[u]) {
            if (minDistances[u] + l < minDistances[v]) {
                minDistances[v] = minDistances[u] + l
            }
        }
    }

    for (i in 1 until landmarksCount + 1) {
        if (minDistances[i] < Int.MAX_VALUE) {
            distances[start][i] = minDistances[i]
        }
    }
}
