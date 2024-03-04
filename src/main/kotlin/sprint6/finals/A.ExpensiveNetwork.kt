package sprint6.finals

/*
    https://contest.yandex.ru/contest/25070/run-report/108569152/

    Этот код реализует алгоритм поиска максимального остовного дерева в графе, используя модифицированный алгоритм Краскала.
    Использование Алгоритма Прима не подходило по ограничению по времени.

    -- ПРИНЦИП РАБОТЫ --
    Алгоритм начинает с сортировки всех рёбер графа по убыванию их веса, чтобы первыми обрабатывались рёбра с максимальным весом.
    Затем, алгоритм последовательно добавляет рёбра в остовное дерево, если это не создаёт циклов, используя для этого структуру данных
    "объединение-поиск" (union-find). Это позволяет эффективно определять, находятся ли две вершины в одной компоненте связности,
    и соединять эти компоненты.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Алгоритм гарантирует, что каждое добавленное ребро увеличивает вес остовного дерева на максимально возможную величину,
    не создавая при этом циклов. Это обеспечивает нахождение максимального остовного дерева, поскольку любое другое дерево
    будет иметь меньший вес из-за выбора рёбер с меньшим весом.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Временная сложность алгоритма составляет O(E log E + E log V), где E - количество рёбер в графе, а V - количество вершин.
    O(E log E) обусловлена сортировкой рёбер, а O(E log V) - операциями "объединения" и "поиска" в структуре "объединение-поиск".

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Пространственная сложность алгоритма составляет O(V + E), где V - количество вершин, а E - количество рёбер в графе.
    Это объясняется необходимостью хранения информации о всех вершинах и рёбрах, а также дополнительной структуре данных для
    операций "объединения-поиска".

    Если граф несвязный и невозможно построить остовное дерево, охватывающее все вершины, выводится сообщение "Oops! I did it again".
 */
data class Vertex(val vertex: Int)
data class Edge(val start: Vertex, val end: Vertex, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = other.weight.compareTo(weight)
}

class Graph(val vertices: MutableList<Vertex> = mutableListOf(), val edges: MutableList<Edge> = mutableListOf())

fun main() {
    val reader = System.`in`.bufferedReader()
    val (verticesCount, edgesCount) = reader.readLine().split(" ").map { it.toInt() }

    if (verticesCount < 2) return println(0)

    val graph = Graph()

    for (v in 1 until verticesCount + 1) {
        graph.vertices += Vertex(v)
    }

    repeat(edgesCount) {
        val (u, v, w) = reader.readLine().split(" ").map { it.toInt() }
        graph.edges += Edge(Vertex(u), Vertex(v), w)
    }

    val mstWeight = findMaximumSpanningTree(graph, verticesCount)
    println(mstWeight ?: "Oops! I did it again")
}

fun findMaximumSpanningTree(graph: Graph, verticesCount: Int): Int? {
    val parent = IntArray(verticesCount + 1) { it }

    val sortedEdges = graph.edges.sorted()

    var weightSum = 0
    var edgesIncluded = 0

    for (edge in sortedEdges) {
        val rootStart = find(edge.start.vertex, parent)
        val rootEnd = find(edge.end.vertex, parent)

        if (rootStart != rootEnd) {
            union(rootStart, rootEnd, parent)
            weightSum += edge.weight
            edgesIncluded++
        }
    }

    return if (edgesIncluded == verticesCount - 1) weightSum else null
}

fun find(x: Int, parent: IntArray): Int {
    if (x != parent[x]) parent[x] = find(parent[x], parent)
    return parent[x]
}

fun union(x: Int, y: Int, parent: IntArray) {
    val rootX = find(x, parent)
    val rootY = find(y, parent)
    parent[rootX] = rootY
}

