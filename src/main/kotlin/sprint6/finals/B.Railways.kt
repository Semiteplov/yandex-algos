package sprint6.finals

/*
    https://contest.yandex.ru/contest/25070/run-report/108646337/

    -- ПРИНЦИП РАБОТЫ --
    Алгоритм основан на построении графа, где каждый город представлен вершиной, а дороги между городами - рёбрами.
    Типы дорог ('R' и 'B') определяют направление ребра в графе. Для дорог типа 'R' направление ребра сохраняется
    от меньшего к большему номеру города, в то время как для дорог типа 'B' направление ребра не учитывается,
    поскольку движение возможно только от меньшего к большему номеру. Задача сводится к проверке графа на наличие циклов.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Используется алгоритм поиска в глубину (DFS) для проверки наличия циклов в графе. Корректность алгоритма подтверждается
    его способностью эффективно обходить граф, отмечая посещённые вершины и вершины в текущем пути обхода. Наличие цикла
    определяется попыткой повторного посещения вершины, которая уже находится в текущем пути обхода.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Временная сложность алгоритма составляет O(V + E), где V - количество вершин (городов), а E - количество рёбер (дорог),
    что является типичной сложностью для алгоритма поиска в глубину.

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Пространственная сложность алгоритма включает в себя хранение графа, что в худшем случае требует O(V^2) памяти,
    если представить граф в виде матрицы смежности. Однако в данной реализации используется список смежности,
    что требует меньше памяти в среднем. Дополнительно используется память для хранения информации о посещённых вершинах
    и вершинах в текущем пути обхода, что составляет O(V).
 */

fun main() {
    val vertexCount = readln().toInt()
    val graph = buildGraph(vertexCount)
    val hasCycle = detectCycleInGraph(graph, vertexCount)
    println(if (hasCycle) "NO" else "YES")
}

fun buildGraph(vertexCount: Int): List<MutableList<Int>> {
    return MutableList(vertexCount) { mutableListOf<Int>() }.apply {
        for (i in 0 until vertexCount - 1) {
            readln().trim().forEachIndexed { index, symbol ->
                val destination = i + index + 1
                if (symbol == 'R') this[i].add(destination) else this[destination].add(i)
            }
        }
    }
}

fun detectCycleInGraph(graph: List<MutableList<Int>>, vertexCount: Int): Boolean {
    val visited = BooleanArray(vertexCount)
    val onStack = BooleanArray(vertexCount)
    return (0 until vertexCount).any { vertex ->
        if (!visited[vertex]) dfs(graph, vertex, visited, onStack) else false
    }
}

fun dfs(
    graph: List<MutableList<Int>>,
    current: Int,
    visited: BooleanArray,
    onStack: BooleanArray
): Boolean {
    if (visited[current]) return false
    visited[current] = true
    onStack[current] = true

    graph[current].forEach { neighbor ->
        if (!visited[neighbor] && dfs(graph, neighbor, visited, onStack) || onStack[neighbor]) return true
    }

    onStack[current] = false
    return false
}
