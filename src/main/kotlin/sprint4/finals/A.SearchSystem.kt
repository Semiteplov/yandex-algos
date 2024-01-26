package sprint4.finals

/*
    Report: https://contest.yandex.ru/contest/24414/run-report/105315516/

    -- ПРИНЦИП РАБОТЫ --
    Алгоритм заполняет таблицу (словарь) вида:

    слово в документе : { индекс документа : частота встречаемости слова в документе }

    То есть для каждого слова хранится вложенная таблица, сопоставляющая индекс документа с частотой этого слова в документе.
    Затем для каждого запроса строится словарь релевантности, где для каждого документа рассчитывается его релевантность.
    Релевантность определяется суммированием частот встречаемости уникальных слов запроса в документе.
    Наконец, выводятся пять самых релевантных документов для каждого запроса.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Каждый документ оценивается по всем словам запроса, что обеспечивает корректное определение его релевантности.
    Словари 'wordToDocFrequencyMap' и 'relevance' гарантируют, что все слова и документы будут учтены.
    Сортировка и ограничение вывода пятью результатами обеспечивает корректный и ограниченный вывод.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Основная временная сложность алгоритма зависит от числа документов, слов в них и слов в запросах.
    Построение индекса имеет сложность O(D * W), где D - количество документов, W - среднее количество слов в документе.
    Обработка запросов имеет сложность O(Q * W), где Q - количество запросов.
    Общая сложность алгоритма составляет O(D * W + Q * W).

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Пространственная сложность зависит от общего числа уникальных слов во всех документах и запросах.
    Так как каждое слово и каждый индекс документа хранятся в словаре, пространственная сложность составляет O(U + D),
    где U - количество уникальных слов, D - количество документов.
 */
class SearchSystem {
    private val wordToDocFrequencyMap = mutableMapOf<String, MutableMap<Int, Int>>()
    private val relevanceComparator = compareByDescending<Map.Entry<Int, Int>> { it.value }.thenBy { it.key }

    fun addDocument(document: String, docIndex: Int) {
        document.split(" ").forEach { word ->
            val frequencies = wordToDocFrequencyMap.getOrPut(word) { mutableMapOf() }
            frequencies.merge(docIndex, 1, Int::plus)
        }
    }

    fun processQuery(query: String): List<Int> {
        val relevance = mutableMapOf<Int, Int>()

        query.split(" ").distinct().forEach { word ->
            wordToDocFrequencyMap[word]?.forEach { (docIndex, frequency) ->
                relevance.merge(docIndex, frequency, Int::plus)
            }
        }

        return relevance.entries
            .sortedWith(relevanceComparator)
            .take(5)
            .map { it.key }
    }
}

fun main() {
    val searchSystem = SearchSystem()

    // Считывание и добавление документов
    val documentsCount = readln().toInt()
    for (docIndex in 0 until documentsCount) {
        searchSystem.addDocument(readln(), docIndex)
    }

    // Считывание и обработка запросов
    val queriesCount = readln().toInt()
    for (queryIndex in 0 until queriesCount) {
        val queryResults = searchSystem.processQuery(readln())
        println(queryResults.joinToString(" ") { (it + 1).toString() })
    }
}