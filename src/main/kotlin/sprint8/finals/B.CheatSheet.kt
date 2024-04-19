package sprint8.finals

// https://contest.yandex.ru/contest/26133/problems/B/

/*
    https://contest.yandex.ru/contest/26133/run-report/112439639/

    -- ПРИНЦИП РАБОТЫ --
    Алгоритм начинается с создания префиксного дерева (trie), в которое добавляются все допустимые слова.
    Это позволяет эффективно искать возможные сегменты слов в заданной строке. При проверке строки используется массив `valid`,
    который отмечает, можно ли достичь каждой позиции строки, начиная от начала, используя слова из trie.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Префиксное дерево гарантирует, что каждый раз, когда мы достигаем конца слова в дереве, мы можем безошибочно установить,
    что сегмент строки соответствует одному из слов в словаре. Массив `valid` помогает отслеживать, какие сегменты строки мы
    можем полностью покрыть используемыми словами, начиная от начала строки.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Построение trie занимает O(L), где L — суммарная длина всех слов в словаре. Поиск возможных разбиений строки занимает
    O(N * M), где N — длина текста, а M — максимальная длина слова в словаре. Итоговая временная сложность составляет
    T * O(L) + O(N * M), где T - количество слов в словаре.

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Память, необходимая для хранения trie, составляет O(L), при условии, что каждое слово имеет уникальный префикс.
    Дополнительно требуется память для массива `valid`, который имеет размер O(N), где N - длина исходной строки.
*/
data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isTerminal: Boolean = false
)

fun addString(root: TrieNode, string: String) {
    var currentNode = root
    for (char in string) {
        currentNode = currentNode.children.getOrPut(char) { TrieNode() }
    }
    currentNode.isTerminal = true
}

fun breakWords(root: TrieNode, string: String): Boolean {
    val valid = BooleanArray(string.length + 1)
    valid[0] = true

    for (pos in string.indices) {
        if (!valid[pos]) continue

        var currentNode = root
        var offset = 0
        while (pos + offset < string.length) {
            val char = string[pos + offset]
            currentNode = currentNode.children[char] ?: break
            if (currentNode.isTerminal) {
                valid[pos + offset + 1] = true
            }
            offset++
        }
    }

    return valid[string.length]
}

fun main() {
    val string = readln()
    val n = readln().toInt()
    val root = TrieNode()

    repeat(n) {
        val word = readln()
        addString(root, word)
    }

    val canBeSegmented = breakWords(root, string)
    println(if (canBeSegmented) "YES" else "NO")
}
