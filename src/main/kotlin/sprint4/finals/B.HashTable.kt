package sprint4.finals

import java.io.BufferedWriter

/*
    Report: https://contest.yandex.ru/contest/24414/run-report/105209349/

    Класс HashTable реализует хеш-таблицу для хранения пар ключ-значение.

    -- ПРИНЦИП РАБОТЫ --
    Хеш-таблица использует метод цепочек для разрешения коллизий.
    Каждый элемент массива 'table' является головой связанного списка (цепочки) узлов 'Node'.
    Каждый 'Node' содержит ключ, значение и ссылку на следующий узел в цепочке.
    Ключи хешируются функцией 'getIndex', которая обеспечивает равномерное распределение по хеш-таблице.
    Для поллучения хэша (индекса) используется функция (key % MAX_TABLE_SIZE + MAX_TABLE_SIZE) % MAX_TABLE_SIZE,
    которая гарантирует, что (хэш) индекс будет лежать в [0, MAX_TABLE_SIZE), даже если ключ < 0.

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
class HashTable {
    companion object {
        const val MAX_TABLE_SIZE = 100003
    }

    private data class Node(val key: Int, var value: Int, var next: Node? = null)

    private val table = arrayOfNulls<Node>(MAX_TABLE_SIZE)

    private fun getIndex(key: Int): Int {
        return (key % MAX_TABLE_SIZE + MAX_TABLE_SIZE) % MAX_TABLE_SIZE
    }

    fun put(key: Int, value: Int) {
        val index = getIndex(key)
        var current = table[index]

        while (current != null) {
            if (current.key == key) {
                current.value = value
                return
            }
            current = current.next
        }

        table[index] = Node(key, value, table[index])
    }

    fun get(key: Int): Int? {
        val index = getIndex(key)
        var current = table[index]

        while (current != null) {
            if (current.key == key) return current.value
            current = current.next
        }

        return null
    }

    fun delete(key: Int): Int? {
        val index = getIndex(key)
        var current = table[index]
        var previos: Node? = null

        while (current != null) {
            if (current.key == key) {
                if (previos == null) {
                    table[index] = current.next
                } else {
                    previos.next = current.next
                }

                return current.value
            }

            previos = current
            current = current.next
        }

        return null
    }
}

fun main() {
    val writer = System.out.bufferedWriter()
    val commandsCount = readln().toInt()
    if (commandsCount == 0) return

    val hashTable = HashTable()
    repeat(commandsCount) {
        val command = readln().split(" ")
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
    this.write(line)
    this.newLine()
}
