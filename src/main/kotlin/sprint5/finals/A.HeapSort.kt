package sprint5.finals

import sprint3.finals.swap
import java.io.BufferedWriter

/*
    https://contest.yandex.ru/contest/24810/run-report/106558187/

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

private fun <T> MutableList<T>.add(element: T, comparator: Comparator<T>) {
    this.add(element)
    this.siftUp(this.lastIndex, comparator)
}

private fun <T> MutableList<T>.siftUp(index: Int, comparator: Comparator<T>) {
    if (index == 0) return
    val parentIndex = (index - 1) / 2

    if (comparator.compare(this[index], this[parentIndex]) > 0) {
        this.swap(index, parentIndex)
        return siftUp(parentIndex, comparator)
    }
}

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
            this[rightChildIndex],
            this[leftChildIndex]
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

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.`out`.bufferedWriter()

    val participantsComparator = compareBy<Participant> { it.solvedProblems } // Ascending order of solved problems
        .thenByDescending { it.penalties } // Descending order of penalties
        .thenByDescending { it.login } // Descending order of login

    val participantsCount = reader.readLine().toInt()
    val participantsHeap = mutableListOf<Participant>()

    repeat(participantsCount) {
        val (login, solvedProblems, penalties) = reader.readLine().split(" ")
        participantsHeap.add(Participant(login, solvedProblems.toInt(), penalties.toInt()), participantsComparator)
    }

    repeat(participantsCount) {
        writer.writeLine(participantsHeap.popMax(participantsComparator).login)
    }
    writer.flush()
}