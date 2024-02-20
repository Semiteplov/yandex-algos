package sprint5.finals

/*
    https://contest.yandex.ru/contest/24810/run-report/107584473/

    -- ПРИНЦИП РАБОТЫ --
    Функция удаления узла из бинарного дерева поиска обрабатывает три основных случая:
    1. Удаляемый узел является листом (не имеет потомков) - узел просто удаляется, его родительский узел теперь ссылается на null.
    2. Удаляемый узел имеет одного потомка - узел удаляется, и его потомок занимает его место, поддерживая структуру дерева.
    3. Удаляемый узел имеет двух потомков - находим узел, который займет его место (минимальный узел из правого поддерева),
    перемещаем его значение в удаляемый узел и рекурсивно удаляем минимальный узел из правого поддерева.

    Этот подход сохраняет свойства бинарного дерева поиска после удаления узла.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Минимальный узел в правом поддереве удаляемого узла гарантированно не имеет левого потомка и является корректным
    заменителем по значениям, сохраняя при этом свойства бинарного дерева поиска. Рекурсивный вызов функции удаления гарантирует,
    что все случаи удаления узлов обрабатываются правильно, и структура дерева после удаления остается корректной.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Временная сложность функции составляет O(h), где h - высота дерева.
    Это потому, что в худшем случае функция будет спускаться от корня к самому глубокому узлу, который необходимо удалить или заменить.

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Пространственная сложность алгоритма составляет O(h) из-за использования стека вызовов при рекурсии.
    В худшем случае, когда дерево вырождено в линейный список, глубина рекурсии может достигать h, где h - высота дерева.

 */

// <template>
class Node(var left: Node?, var right: Node?, var value: Int)
// <template>

fun remove(root: Node?, key: Int): Node? {
    if (root == null) return null

    when {
        key < root.value -> root.left = remove(root.left, key)
        key > root.value -> root.right = remove(root.right, key)
        else -> {
            if (root.left == null) return root.right
            if (root.right == null) return root.left

            var parentOfMin = root
            var minNode = root.right
            while (minNode?.left != null) {
                parentOfMin = minNode
                minNode = minNode.left
            }

            minNode?.let {
                root.value = it.value

                if (parentOfMin == root) {
                    parentOfMin.right = it.right
                } else {
                    parentOfMin?.left = it.right
                }
            }
        }
    }
    return root
}

fun test() {
    val node1 = Node(null, null, 2)
    val node2 = Node(node1, null, 3)
    val node3 = Node(null, node2, 1)
    val node4 = Node(null, null, 6)
    val node5 = Node(node4, null, 8)
    val node6 = Node(node5, null, 10)
    val node7 = Node(node3, node6, 5)
    val newHead = remove(node7, 10)
    assert(newHead!!.value == 5)
    assert(newHead?.right == node5)
    assert(newHead?.right!!.value == 8)
}