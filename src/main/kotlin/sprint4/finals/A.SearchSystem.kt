package sprint4.finals

/*
    Report: https://contest.yandex.ru/contest/24414/run-report/105212685/

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
fun main() {
    val documents = readStrings()
    val queries = readStrings()

    val wordToDocFrequencyMap = mutableMapOf<String, MutableMap<Int, Int>>() // word : { document ID : frequency }

    for (i in documents.indices) {
        val words = documents[i].split(" ")

        for (word in words) {
            val currentMap = wordToDocFrequencyMap.getOrDefault(word, mutableMapOf())
            currentMap[i] = currentMap.getOrDefault(i, 0) + 1
            wordToDocFrequencyMap[word] = currentMap
        }
    }


    for (query in queries) {
        val relevance = mutableMapOf<Int, Int>()

        val words = query.split(" ").toSet() // слова из запрса должны быть уникальными

        for (word in words) {
            val wordFrequencies = wordToDocFrequencyMap[word]
            if (wordFrequencies != null) {
                for ((docIndex, frequency) in wordFrequencies) {
                    relevance[docIndex] = relevance.getOrDefault(docIndex, 0) + frequency
                }
            }
        }

        relevance.entries.sortedWith(compareByDescending<MutableMap.MutableEntry<Int, Int>> { it.value }.thenBy { it.key })
            .take(5).forEach { print("${it.key + 1} ") }
        println()
    }
}

private fun readStrings(): List<String> {
    val stringsCount = readln().toInt()
    return List(stringsCount) { readln() }
}