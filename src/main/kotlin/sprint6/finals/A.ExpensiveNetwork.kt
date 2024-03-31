package sprint6.finals

/*
    https://contest.yandex.ru/contest/25070/run-report/108994498/

    Этот код реализует алгоритм поиска максимального остовного дерева в графе, используя модифицированный алгоритм Краскала.
    Использование Алгоритма Прима не подходило по ограничению по времени.

    -- ПРИНЦИП РАБОТЫ --
    Алгоритм начинает с сортировки всех рёбер графа по убыванию их веса, чтобы первыми обрабатывались рёбра с максимальным весом.
    Затем, алгоритм последовательно добавляет рёбра в остовное дерево, если это не создаёт циклов, используя для этого структуру данных
    "объединение-поиск" (union-find). Это позволяет эффективно определять, находятся ли две вершины в одной компоненте связности,
    и соединять эти компоненты.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Алгоритм гарантирует, что каждое добавленное ребро увеличивает вес остовного дерева на максимально возможную величину,
    не создавая при этом циклов. Проверка на условие `edgesIncluded == verticesCount - 1` обеспечивает, что построенное
    дерево является остовным: оно охватывает все вершины графа, содержит `V - 1` рёбер.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Временная сложность алгоритма составляет O(E log E + E log V), где E - количество рёбер в графе, а V - количество вершин.
    O(E log E) обусловлена сортировкой рёбер, а O(E log V) - операциями "объединения" и "поиска" в структуре "объединение-поиск".

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Пространственная сложность алгоритма составляет O(V + E), где V - количество вершин, а E - количество рёбер в графе.
    Это объясняется необходимостью хранения информации о всех вершинах и рёбрах, а также дополнительной структуре данных для
    операций "объединения-поиска".

    Если граф несвязный и невозможно построить остовное дерево, охватывающее все вершины, выводится сообщение "Oops! I did it again".
 */
typealias Vertex = Int

data class Edge(val start: Vertex, val end: Vertex, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = other.weight.compareTo(weight)
}

class Graph(val edges: MutableList<Edge> = mutableListOf())

fun main() {
    val reader = System.`in`.bufferedReader()
    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }

    val graph = Graph()

    repeat(edgesCount) {
        val (u, v, w) = reader.readLine().split(" ").map { it.toInt() }
        graph.edges += Edge(u, v, w)
    }

    val mstWeight = findMaximumSpanningTree(graph, verticesCount)
    println(mstWeight ?: "Oops! I did it again")
}

fun findMaximumSpanningTree(graph: Graph, verticesCount: Int): Int? {
    val dsu = DisjointSetUnion(verticesCount + 1)

    val sortedEdges = graph.edges.sorted()

    var weightSum = 0
    var edgesIncluded = 0

    for (edge in sortedEdges) {
        val rootStart = dsu.find(edge.start)
        val rootEnd = dsu.find(edge.end)

        if (rootStart != rootEnd) {
            dsu.union(rootStart, rootEnd)
            weightSum += edge.weight
            edgesIncluded++
        }
    }

    return if (edgesIncluded == verticesCount - 1) weightSum else null
}

class DisjointSetUnion(size: Int) {
    private val parent: IntArray = IntArray(size) { it }
    private val rank: IntArray = IntArray(size) { 0 }

    fun find(x: Int): Int {
        if (x != parent[x]) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        var rootX = find(x)
        var rootY = find(y)
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                val temp = rootX
                rootX = rootY
                rootY = temp
            }
            parent[rootY] = rootX
            if (rank[rootX] == rank[rootY]) {
                rank[rootX]++
            }
        }
    }
}