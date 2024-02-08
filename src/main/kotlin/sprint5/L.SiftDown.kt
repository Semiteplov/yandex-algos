package sprint5

fun siftDown(heap: IntArray, idx: Int): Int {
    val leftIndex = idx * 2
    val rightIndex = idx * 2 + 1

    if (heap.lastIndex < leftIndex) return idx

    val largestIndex = if (rightIndex <= heap.lastIndex && heap[leftIndex] < heap[rightIndex]) rightIndex else leftIndex

    if (heap[largestIndex] > heap[idx]) {
        val temp = heap[largestIndex]
        heap[largestIndex] = heap[idx]
        heap[idx] = temp
        return siftDown(heap, largestIndex)

    }

    return idx
}


fun testL() {
    val sample = intArrayOf(-1, 12, 1, 8, 3, 4, 7)
    print(siftDown(sample, 2))
    assert(siftDown(sample, 2) == 5)
}

fun main() {
    testL()
}