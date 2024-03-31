package sprint6.finals

/*
    https://contest.yandex.ru/contest/25070/run-report/109123517/

    -- ПРИНЦИП РАБОТЫ --
    Алгоритм основан на построении графа, где каждый город представлен вершиной, а дороги между городами - рёбрами.
    Типы дорог ('R' и 'B') определяют направление ребра в графе. Для дорог типа 'R' направление ребра сохраняется
    от меньшего к большему номеру города, в то время как для дорог типа 'B' направление ребра не учитывается,
    поскольку движение возможно только от меньшего к большему номеру.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Используется алгоритм поиска в глубину (DFS), где каждая вершина может быть окрашена в один из трех цветов:
    белый (WHITE) - вершина не посещена, серый (GRAY) - вершина посещена и находится в текущем пути обхода, и черный (BLACK) - вершина
    полностью обработана.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Временная сложность алгоритма составляет O(V + E), где V - количество вершин (городов), а E - количество рёбер (дорог),
    что является типичной сложностью для алгоритма поиска в глубину.

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Пространственная сложность алгоритма включает в себя хранение графа, что в худшем случае требует O(V^2) памяти,
    если представить граф в виде матрицы смежности. Однако в данной реализации используется список смежности,
    что требует меньше памяти в среднем. Дополнительно используется память для хранения информации о посещённых вершинах
    и вершинах в текущем пути обхода, что составляет O(V).
 */
enum class Colors {
    WHITE, GRAY, BLACK
}

fun main() {
    val vertexCount = readln().toInt()
    val graph = buildGraph(vertexCount)
    val hasCycle = detectCycleInGraph(graph)
    println(if (hasCycle) "NO" else "YES")
}

fun buildGraph(vertexCount: Int): List<List<Int>> {
    return MutableList(vertexCount) { mutableListOf<Int>() }.apply {
        for (i in 0 until vertexCount - 1) {
            readln().trim().forEachIndexed { index, symbol ->
                val destination = i + index + 1
                if (symbol == 'R') this[i].add(destination) else this[destination].add(i)
            }
        }
    }
}

fun detectCycleInGraph(graph: List<List<Int>>): Boolean {
    val vertexCount = graph.size
    val colors = Array(vertexCount) { Colors.WHITE }
    for (vertex in 0 until vertexCount) {
        if (colors[vertex] == Colors.WHITE) {
            if (dfs(graph, vertex, colors)) return true
        }
    }
    return false
}

fun dfs(
    graph: List<List<Int>>,
    current: Int,
    colors: Array<Colors>
): Boolean {
    if (colors[current] == Colors.GRAY) return true
    if (colors[current] == Colors.BLACK) return false

    colors[current] = Colors.GRAY
    for (neighbor in graph[current]) {
        if (dfs(graph, neighbor, colors)) return true
    }
    colors[current] = Colors.BLACK
    return false
}
