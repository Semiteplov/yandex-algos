package sprint5

fun siftUp(heap: IntArray, idx: Int): Int {
    if (idx == 1) return idx
    val vertexIndex = idx / 2

    if (heap[vertexIndex] < heap[idx]) {
        val temp = heap[vertexIndex]
        heap[vertexIndex] = heap[idx]
        heap[idx] = temp

        return siftUp(heap, vertexIndex)
    }
    return idx
}

fun siftUpIterative(heap: IntArray, idx: Int): Int {
    var currentIndex = idx

    while (currentIndex > 1) {
        val parentIndex = currentIndex / 2

        // Если текущий узел больше своего родителя, меняем их местами
        if (heap[currentIndex] > heap[parentIndex]) {
            val temp = heap[currentIndex]
            heap[currentIndex] = heap[parentIndex]
            heap[parentIndex] = temp

            currentIndex = parentIndex // Перемещаем индекс к родителю
        } else {
            break // Если текущий узел меньше или равен родителю, завершаем цикл
        }
    }

    return currentIndex // Возвращаем финальный индекс элемента
}

fun testM() {
    val sample = intArrayOf(-1, 12, 6, 8, 3, 15, 7)
    assert(siftUp(sample, 5) == 1)
}