package sprint5.finals

import sprint3.finals.swap
import java.io.BufferedWriter

/*
    https://contest.yandex.ru/contest/24810/run-report/107584803/

    Эта задача реализует алгоритм сортировки кучей (Heapsort) для таблицы результатов соревнования по программированию.
    Сортировка выполняется на основе количества решенных задач, размера штрафа и лексикографического порядка логинов участников.
    Каждый участник представлен классом Participant, содержащим его логин, количество решенных задач и штраф.

    -- ПРИНЦИП РАБОТЫ --
    В алгоритме применяются операции просеивания вверх и вниз непосредственно к массиву участников.
    Функция add добавляет новый элемент в массив и просеивает его вверх до корректной позиции для max-heap.
    Функция popMax извлекает максимальный элемент (по компаратору) из массива, обеспечивая правильный порядок сортировки.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Алгоритм корректно сортирует участников в соответствии с заданными критериями благодаря правильно реализованным операциям просеивания вверх и вниз.
    Компаратор гарантирует, что элементы в куче упорядочены правильно на каждом шаге алгоритма.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Временная сложность добавления элемента в кучу и операции просеивания составляет O(log n), где n - количество элементов в куче.
    Таким образом, общая временная сложность заполнения кучи составляет O(n log n).
    Извлечение всех элементов из кучи также занимает O(n log n) времени.

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Поскольку все операции выполняются непосредственно на массиве участников, дополнительная пространственная сложность алгоритма составляет O(1).
    Это делает алгоритм пространственно эффективным, так как не требует дополнительного места,
    за исключением небольшого количества временных переменных для операций просеивания.

*/

private data class Participant(val login: String, val solvedProblems: Int, val penalties: Int)


private fun <T> MutableList<T>.popMax(comparator: Comparator<T>): T {
    val maxItem = this.first()
    val lastIndex = this.lastIndex
    this[0] = this[lastIndex]
    this.removeAt(this.lastIndex)

    if (this.isNotEmpty()) siftDown(0, comparator)

    return maxItem
}

private fun <T> MutableList<T>.siftDown(index: Int, comparator: Comparator<T>) {
    val leftChildIndex = index * 2 + 1
    val rightChildIndex = index * 2 + 2
    val lastItemIndex = this.lastIndex

    if (leftChildIndex > lastItemIndex) return

    val largestItemIndex = if (rightChildIndex <= lastItemIndex && comparator.compare(
            this[rightChildIndex], this[leftChildIndex]
        ) > 0
    ) rightChildIndex else leftChildIndex

    if (comparator.compare(this[largestItemIndex], this[index]) > 0) {
        this.swap(index, largestItemIndex)
        return siftDown(largestItemIndex, comparator)
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

private fun <T> MutableList<T>.heapify(comparator: Comparator<T>) {
    for (i in this.size / 2 - 1 downTo 0) {
        this.siftDown(i, comparator)
    }
}

private fun <T> MutableList<T>.heapSort(comparator: Comparator<T>) {
    this.heapify(comparator)
    for (i in this.lastIndex downTo 1) {
        this.swap(0, i)
        val tmpList = this.subList(0, i)
        tmpList.siftDown(0, comparator)
    }
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.`out`.bufferedWriter()

    val participantsComparator = compareByDescending<Participant> { it.solvedProblems }
        .thenBy { it.penalties }
        .thenBy { it.login }

    val participantsCount = reader.readLine().toInt()
    val participants = mutableListOf<Participant>()

    repeat(participantsCount) {
        val (login, solvedProblems, penalties) = reader.readLine().split(" ")
        participants += Participant(login, solvedProblems.toInt(), penalties.toInt())
    }

    participants.heapSort(participantsComparator)

    participants.forEach {
        writer.writeLine(it.login)
    }
    writer.flush()
}