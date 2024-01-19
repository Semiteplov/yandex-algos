package sprint4.finals

import java.io.BufferedWriter
import java.lang.Math.floorMod

/*
    Report: https://contest.yandex.ru/contest/24414/run-report/105315573/

    Класс HashTable реализует хеш-таблицу для хранения пар ключ-значение.

    -- ПРИНЦИП РАБОТЫ --
    Хеш-таблица использует метод цепочек для разрешения коллизий.
    Каждый элемент массива 'table' является головой связанного списка (цепочки) узлов 'Node'.
    Каждый 'Node' содержит ключ, значение и ссылку на следующий узел в цепочке.
    Ключи хешируются функцией 'getIndex', которая обеспечивает равномерное распределение по хеш-таблице.
    Для поллучения хэша (индекса) используется функция Math.floorMod,
    которая гарантирует, что (хэш) индекс будет лежать в [0, tableSize), даже если ключ < 0.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Алгоритм гарантирует уникальность индекса каждого ключа в таблице или его добавление в список узлов,
    связанных с этим индексом. Операции 'put', 'get' и 'delete' корректно обрабатывают добавление,
    поиск и удаление элементов, учитывая возможные коллизии.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    В среднем случае операции 'put', 'get', и 'delete' имеют сложность O(1).
    В худшем случае, когда все ключи попадают в одну цепочку, сложность операций увеличивается до O(n).

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Пространственная сложность составляет O(n), где n — общее количество сохраненных ключей.
 */
class HashTable(private val tableSize: Int = 100003) {

    private data class Node(val key: Int, var value: Int, var next: Node? = null)

    private val table = arrayOfNulls<Node>(tableSize)

    private fun getIndex(key: Int): Int {
        return floorMod(key, tableSize)
    }

    private fun <T> findNode(key: Int, action: (node: Node?, previousNode: Node?, bucketIndex: Int) -> T): T {
        val index = getIndex(key)
        var current = table[index]
        var previous: Node? = null

        while (current != null) {
            if (current.key == key) {
                break
            }
            previous = current
            current = current.next
        }

        return action(current, previous, index)
    }

    fun put(key: Int, value: Int) {
        findNode(key) { currentNode, _, index ->
            if (currentNode != null) {
                currentNode.value = value
            } else {
                table[index] = Node(key, value, table[index])
            }
        }
    }

    fun get(key: Int): Int? {
        return findNode(key) { currentNode, _, _ ->
            currentNode?.value
        }
    }

    fun delete(key: Int): Int? {
        return findNode(key) { currentNode, previousNode, index ->
            currentNode?.let {
                if (previousNode == null) {
                    table[index] = currentNode.next
                } else {
                    previousNode.next = currentNode.next
                }
                currentNode.value
            }
        }
    }
}

fun main() {
    val writer = System.out.bufferedWriter()
    val reader = System.`in`.bufferedReader()
    val commandsCount = reader.readLine().toInt()
    if (commandsCount == 0) return

    val hashTable = HashTable()
    repeat(commandsCount) {
        val command = reader.readLine().split(" ")
        val operation = command[0]
        val key = command[1].toInt()

        when (operation) {
            "put" -> {
                val value = command[2].toInt()
                hashTable.put(key, value)
            }

            "get" -> writer.writeLine(hashTable.get(key)?.toString() ?: "None")
            "delete" -> writer.writeLine(hashTable.delete(key)?.toString() ?: "None")
        }
    }

    writer.flush()
}

fun BufferedWriter.writeLine(line: String) {
    write(line)
    newLine()
}
