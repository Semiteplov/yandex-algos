package sprint5.finals

import sprint3.finals.swap
import java.io.BufferedWriter

/*
    https://contest.yandex.ru/contest/24810/run-report/106552979/

    Эта задача реализует алгоритм сортировки кучей (Heapsort) для таблицы результатов соревнования по программированию.
    Сортировка выполняется на основе количества решенных задач, размера штрафа и лексикографического порядка логинов участников.
    Каждый участник представлен классом Participant, содержащим его логин, количество решенных задач и штраф.

    -- ПРИНЦИП РАБОТЫ --
    Для сортировки используется структура данных - максимальная куча. Компаратор для кучи устроен таким образом,
    что участник с наибольшим количеством решенных задач находится на вершине кучи.
    При равенстве количества решенных задач выше будет тот, у кого меньше штраф.
    Если и штрафы одинаковы, сравниваются логины участников.
    Это обеспечивает необходимый порядок сортировки в соответствии с условиями.

    В процессе добавления нового участника в кучу используется операция просеивания вверх, чтобы восстановить свойства максимальной кучи.
    При извлечении элемента из кучи (получении участника с максимальным приоритетом) используется просеивание вниз.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Алгоритм корректно сортирует участников в соответствии с заданными критериями благодаря правильно реализованным операциям просеивания вверх и вниз.
    Компаратор гарантирует, что элементы в куче упорядочены правильно на каждом шаге алгоритма.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Временная сложность добавления элемента в кучу и операции просеивания составляет O(log n), где n - количество элементов в куче.
    Таким образом, общая временная сложность заполнения кучи составляет O(n log n).
    Извлечение всех элементов из кучи также занимает O(n log n) времени.

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Пространственная сложность алгоритма составляет O(n), так как все участники хранятся внутри кучи.
    Дополнительное пространство используется для компаратора и временных переменных при операциях просеивания,
    но это не влияет на общую пространственную сложность.

*/

private data class Participant(val login: String, val solvedProblems: Int, val penalties: Int)

class Heap<T>(private val comparator: Comparator<T>) {

    private val heap = mutableListOf<T>()

    fun add(item: T) {
        heap.add(item)
        siftUp(heap.lastIndex)
    }

    private fun siftUp(index: Int) {
        if (index == 0) return

        val parentIndex = (index - 1) / 2

        if (comparator.compare(heap[index], heap[parentIndex]) > 0) {
            heap.swap(index, parentIndex)
            return siftUp(parentIndex)
        }
    }

    fun popMax(): T {
        val maxItem = heap.first()
        val lastIndex = heap.lastIndex
        heap[0] = heap[lastIndex]
        heap.removeAt(heap.lastIndex)

        if (heap.isNotEmpty()) siftDown(0)

        return maxItem
    }

    private fun siftDown(index: Int = 1) {
        val leftChildIndex = index * 2 + 1
        val rightChildIndex = index * 2 + 2
        val lastItemIndex = heap.lastIndex

        // no children
        if (leftChildIndex > lastItemIndex) return

        val largestItemIndex = if (rightChildIndex <= lastItemIndex && comparator.compare(
                heap[rightChildIndex],
                heap[leftChildIndex]
            ) > 0
        ) rightChildIndex else leftChildIndex

        if (comparator.compare(heap[largestItemIndex], heap[index]) > 0) {
            heap.swap(index, largestItemIndex)
            return siftDown(largestItemIndex)
        }
    }
}

private fun <T> MutableList<T>.swap(firstIndex: Int, secondIndex: Int) {
    val temp = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = temp
}

private fun BufferedWriter.writeLine(line: String) {
    this.write(line)
    this.newLine()
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.`out`.bufferedWriter()

    val participantsComparator = compareBy<Participant> { it.solvedProblems } // Ascending order of solved problems
        .thenByDescending { it.penalties } // Descending order of penalties
        .thenByDescending { it.login } // Descending order of login
    val participantHeap = Heap(participantsComparator)

    val participantsCount = reader.readLine().toInt()
    repeat(participantsCount) {
        val (login, solvedProblems, penalties) = reader.readLine().split(" ")
        participantHeap.add(Participant(login, solvedProblems.toInt(), penalties.toInt()))
    }

    repeat(participantsCount) {
        writer.writeLine(participantHeap.popMax().login)
    }
    writer.flush()
}