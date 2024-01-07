package sprint3.finals

/*
    https://contest.yandex.ru/contest/23815/run-report/104320757/

    -- ПРИНЦИП РАБОТЫ --
    Алгоритм использует модификацию быстрой сортировки (in-place) для упорядочивания участников соревнования.
    Участники сортируются сначала по количеству решенных задач, затем по штрафам, и, наконец, по логину.
    Метод partition выбирает опорный элемент (pivot), а затем переупорядочивает список так, чтобы элементы
    меньше опорного оказались слева от него, а больше - справа. Алгоритм рекурсивно применяется к левой и правой части.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Быстрая сортировка корректно работает, так как на каждом шаге алгоритма элементы разделяются относительно опорного.
    Элементы, которые должны быть до опорного, перемещаются на его левую сторону, а которые после - на правую.
    Таким образом, после каждого вызова partition опорный элемент оказывается на своем финальном месте в отсортированном списке.
    Рекурсивное применение к подмассивам гарантирует полную сортировку списка.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    В среднем случае быстрая сортировка имеет временную сложность O(n log n), где n - количество элементов в списке.
    Это обусловлено тем, что на каждом уровне рекурсии происходит деление списка на две примерно равные части,
    и каждый уровень рекурсии требует O(n) времени для выполнения partition.
    В худшем случае, когда каждый раз выбирается крайний элемент в качестве опорного, сложность составляет O(n^2).

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Быстрая сортировка in-place не требует дополнительного пространства, кроме стека вызовов,
    глубина которого в среднем случае O(log n) и в худшем O(n). Таким образом,
    пространственная сложность алгоритма составляет O(log n) в среднем случае и O(n) в худшем.
*/
data class Participant(val login: String, val solvedProblems: Int, val penalties: Int)

fun <T> MutableList<T>.swap(firstIndex: Int, secondIndex: Int) {
    val temp = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = temp
}

fun <T> partition(arr: MutableList<T>, left: Int, right: Int, comparator: Comparator<T>): Int {
    val pivot = arr[right]
    var index = left - 1

    for (currentIndex in left until right) {
        if (comparator.compare(arr[currentIndex], pivot) < 0) {
            index++
            arr.swap(index, currentIndex)
        }
    }

    val newPositionOfPivot = index + 1
    arr.swap(newPositionOfPivot, right)

    return newPositionOfPivot
}

fun <T> quickSort(arr: MutableList<T>, left: Int = 0, right: Int = arr.lastIndex, comparator: Comparator<T>) {
    if (left < right) {
        val pivot = partition(arr, left, right, comparator)

        quickSort(arr, left, pivot - 1, comparator)
        quickSort(arr, pivot + 1, right, comparator)
    }
}


fun main() {
    val participantsCount = readln().toInt()
    val participants = mutableListOf<Participant>()

    repeat(participantsCount) {
        val (login, solvedProblems, penalties) = readln().split(" ")
        participants.add(Participant(login, solvedProblems.toInt(), penalties.toInt()))
    }

    val participantComparator = compareBy<Participant>(
        { -it.solvedProblems },
        { it.penalties },
        { it.login },
    )

    quickSort(participants, comparator = participantComparator)

    println(participants.joinToString(separator = "\n") { it.login })
}