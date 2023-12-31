package sprint2.finals

import java.util.Stack

/*
 * https://contest.yandex.ru/contest/22781/run-report/104003778/
 *
 * Этот алгоритм реализует базовый калькулятор обратной польской записи с использованием стека из коробки.
 * Принцип работы:
 * - Считывает входную строку чисел и операторов.
 * - Числа помещаются в стек.
 * - При встрече оператора алгоритм извлекает необходимое количество операндов из стека,
 *   применяет оператор и возвращает результат обратно в стек.
 * - После выполнения последней операции на вершине стека находится результат.
 *
 * Обоснование корректности:
 * - Алгоритм корректно реализует метод расчета ОПЗ, который является устоявшимся математическим
 *   методом для вычисления выражений без необходимости в скобках.
 * - Каждый оператор корректно выполняет свою математическую функцию над двумя последними числами, что соответствует
 *   логике LIFO.
 * - Специальная обработка случая деления обеспечивает правильное округление результатов при делении отрицательных чисел.
 *
 * Временная сложность:
 * - O(n), где n - количество элементов на входе (как числа, так и операторы).
 * - Каждый элемент обрабатывается ровно один раз.
 *
 * Пространственная сложность:
 * - O(n) в худшем случае, когда все элементы являются числами и хранятся в стеке до любой операции.
 * - Обычно размер стека меньше n, так как операторы уменьшают размер стека.
 */
fun main() {
    val operators = buildMap {
        put("+") { a: Int, b: Int -> a + b }
        put("-") { a: Int, b: Int -> a - b }
        put("*") { a: Int, b: Int -> a * b }
        put("/") { a: Int, b: Int -> Math.floorDiv(a, b) }
    }

    val stack = Stack<Int>()

    val input = readln().split(" ")
    for (s in input) {
        val operation = operators[s]

        if (operation != null) {
            val last = stack.pop()
            val beforeLast = stack.pop()
            stack.push(operation(beforeLast, last))
        } else {
            val number = s.toIntOrNull() ?: error("Invalid input: '$s' is neither an integer nor a valid operator")
            stack.push(number)
        }
    }
    println(stack.peek())
}