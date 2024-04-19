package sprint8.finals

import java.util.Stack

// https://contest.yandex.ru/contest/26133/problems/A/

/*
    https://contest.yandex.ru/contest/26133/run-report/112438839/

    -- ПРИНЦИП РАБОТЫ --
    Для решения задачи мы используем два основных метода:
        1. Распаковка строки: метод обрабатывает строку посимвольно, используя стек для управления вложенными структурами
           и числовыми множителями. Каждый раз при встрече ']' мы распаковываем текущую последовательность в соответствии
           с последним сохраненным множителем в стеке.

        2. Поиск наибольшего общего префикса: метод сравнивает строки посимвольно, сокращая префикс до тех пор,
           пока он не станет общим для всех строк или пока не закончатся символы.

    -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Распаковка с использованием стека гарантирует, что все операции с учетом вложенности и множителей выполняются
    корректно, так как стек помогает управлять текущими активными областями текста. Поиск наибольшего общего префикса
    эффективен, так как он последовательно сужает возможные варианты, основываясь на сравнении строк.

    -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
        - Распаковка строки может занять O(M * N * K), где M - длина строки после полной распаковки,
          N - количество строк, K - глубина вложенности множителей.
        - Поиск общего префикса имеет сложность O(M * N), где M - максимальная длина распакованной строки,
          N - количество строк.

    -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
        - При распаковке строки наибольший объем памяти требуется для хранения распакованной строки, что составляет O(M).
        - При поиске префикса используется дополнительная память для хранения текущего префикса, что не превышает O(M).
*/

fun main() {
    val n = readln().toInt()
    val strings = List(n) { readln() }
    val unpackedStrings = strings.map { unpack(it) }
    val commonPrefix = findLongestCommonPrefix(unpackedStrings)
    println(commonPrefix)
}

fun unpack(string: String): String {
    val stack = Stack<Pair<StringBuilder, Int>>()
    var current = StringBuilder()
    var multiplier = 0

    for (char in string) {
        when {
            char.isDigit() -> multiplier = multiplier * 10 + (char - '0')
            char == '[' -> {
                stack.push(current to multiplier)
                current = StringBuilder()
                multiplier = 0
            }

            char == ']' -> {
                val (lastString, times) = stack.pop()
                val expanded = current.toString().repeat(times)
                current = lastString.append(expanded)
            }

            else -> current.append(char)
        }
    }

    return current.toString()
}

fun findLongestCommonPrefix(strings: List<String>): String {
    if (strings.isEmpty()) return ""
    var prefix = strings[0]

    for (string in strings) {
        while (string.indexOf(prefix) != 0) {
            if (prefix.isEmpty()) return ""
            prefix = prefix.substring(0, prefix.length - 1)
        }
    }

    return prefix
}
