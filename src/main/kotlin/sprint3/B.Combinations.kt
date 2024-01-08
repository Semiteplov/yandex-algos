package sprint3

// https://contest.yandex.ru/contest/23638/problems/B/

fun main() {
    val input = readln()
    val phoneMap = mapOf(
        '2' to "abc", '3' to "def", '4' to "ghi", '5' to "jkl",
        '6' to "mno", '7' to "pqrs", '8' to "tuv", '9' to "wxyz"
    )
    val combinations = mutableListOf<String>()
    generateCombinations("", input, 0, phoneMap, combinations)
    println(combinations.joinToString(" "))
}

fun generateCombinations(
    prefix: String,
    digits: String,
    index: Int,
    phoneMap: Map<Char, String>,
    combinations: MutableList<String>
) {
    if (index == digits.length) {
        combinations.add(prefix)
        return
    }

    val letters = phoneMap[digits[index]] ?: ""
    for (letter in letters) {
        generateCombinations(prefix + letter, digits, index + 1, phoneMap, combinations)
    }
}
