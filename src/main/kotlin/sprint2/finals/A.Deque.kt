package sprint2.finals

/*
 * https://contest.yandex.ru/contest/22781/run-report/103687172/
 *
 * Алгоритм Deque (двусторонняя очередь) на основе кольцевого буфера
 *
 * Принцип работы:
 * Дек (двусторонняя очередь) реализован с использованием кольцевого буфера,
 * который позволяет эффективно добавлять и удалять элементы с обоих концов.
 * Кольцевой буфер — это структура данных, использующая единственный фиксированный
 * массив и два индекса (front и back) для отслеживания начала и конца очереди.
 * Операции push и pop модифицируют эти индексы соответственно, обеспечивая
 * циклическое использование массива.
 *
 * Корректность работы:
 * Алгоритм корректен, так как для каждой операции push и pop мы соответственно
 * увеличиваем или уменьшаем размер очереди и корректно обновляем индексы front и back.
 * Кольцевая структура массива обеспечивает, что при достижении конца массива
 * следующий элемент будет добавлен или удален с начала массива, тем самым сохраняя
 * непрерывность структуры данных.
 *
 * Временная сложность:
 * Все операции (pushBack, pushFront, popFront, popBack) выполняются за O(1),
 * так как они включают в себя лишь несколько простых арифметических операций и
 * одно присваивание/получение значения из массива.
 *
 * Пространственная сложность:
 * Пространственная сложность алгоритма составляет O(n), где n — максимальный размер дека.
 * Это объясняется использованием массива фиксированного размера для хранения элементов дека.
 */
class Deque(private val maxSize: Int) {
    private val deque = IntArray(maxSize)
    private var front = 0
    private var back = 0
    private var size = 0

    private val maxIndex = maxSize - 1

    private fun isFull() = size == maxSize
    private fun isEmpty() = size == 0

    fun pushBack(value: Int): String? {
        if (isFull()) {
            return "error"
        }
        deque[back] = value
        back = (back + 1) % maxSize
        size++
        return null
    }

    fun pushFront(value: Int): String? {
        if (isFull()) {
            return "error"
        }
        front = (front + maxIndex) % maxSize
        deque[front] = value
        size++
        return null
    }

    fun popFront(): String {
        if (isEmpty()) {
            return "error"
        }
        val value = deque[front]
        front = (front + 1) % maxSize
        size--
        return value.toString()
    }

    fun popBack(): String {
        if (isEmpty()) {
            return "error"
        }
        back = (back + maxIndex) % maxSize
        val value = deque[back]
        size--
        return value.toString()
    }
}

fun main() {
    // Buffers are used for reducing time complexity
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val commands = reader.readLine().toInt()
    val maxSize = reader.readLine().toInt()
    val deque = Deque(maxSize)

    repeat(commands) {
        val input = reader.readLine().split(" ")
        when (input[0]) {
            "push_back" -> deque.pushBack(input[1].toInt())?.let { writer.write("$it\n") }
            "push_front" -> deque.pushFront(input[1].toInt())?.let { writer.write("$it\n") }
            "pop_front" -> writer.write("${deque.popFront()}\n")
            "pop_back" -> writer.write("${deque.popBack()}\n")
        }
    }

    writer.flush()
}
