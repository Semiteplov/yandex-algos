package sprint7.finals

/*
    https://contest.yandex.ru/contest/25597/run-report/110999446/

    -- ПРИНЦИП РАБОТЫ --
    В данной задаче рассчитывается расстояние Левенштейна между двумя последовательностями.

    За базовый случай мы берем длину последовательности.

    С помощью динамического программирования алгоритм сравнивает символы строк, находящиеся на предыдущих позициях:
        - если символы совпадают, используется значение, рассчитанное на предыдущем шаге,
        - в противном случае выбирается минимальное и добавляется 1 к результату.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Алгоритм корректности можно доказать методом математической индукции, основываясь на правильности выбора минимальной стоимости операций для каждого шага.
    Так как у нас возрастающая двумерная поверхность, то при доказательстве от противного имеем Mn-1k-1 > Mnk + 1 => противоречие.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Временная сложность алгоритма составляет O(M * N), где N - длина первой последовательности, а M - длина второй последовательности.
    Это обусловлено необходимостью обхода каждого элемента матрицы расстояний один раз.

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Алгоритм использует O(N) пространства, т.к. в памяти мы храним и заполняем значения в двух последовательностях символов длины N
 */
fun calculateLevenshteinDistance(string1: String, string2: String): Int {
    val len1 = string1.length
    val len2 = string2.length

    if (len2 == 0 || len1 == 0) return maxOf(len1, len2)

    var previousRow = IntArray(len1 + 1) { it }
    var currentRow = IntArray(len1 + 1)

    for (i in 1..len2) {
        currentRow[0] = i
        for (j in 1..len1) {
            val deletionCost = previousRow[j] + 1
            val insertionCost = currentRow[j - 1] + 1
            val substitutionCost = previousRow[j - 1] + if (string2[i - 1] == string1[j - 1]) 0 else 1

            currentRow[j] = minOf(deletionCost, insertionCost, substitutionCost)
        }
        val temp = previousRow
        previousRow = currentRow
        currentRow = temp
    }

    return previousRow[len1]
}

fun main() {
    val first = readln()
    val second = readln()

    val distance = calculateLevenshteinDistance(first, second)
    println(distance)
}