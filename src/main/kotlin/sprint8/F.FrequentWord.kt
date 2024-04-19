package sprint8

// https://contest.yandex.ru/contest/26131/problems/F/
fun main() {
    val n = readln().trim().toInt()
    val words = HashMap<String, Int>()

    for (i in 0 until n) {
        val word = readln().trim()

        if (words.containsKey(word)) {
            words[word] = words[word]!! + 1
        } else {
            words[word] = 1
        }
    }

    var frequency = 0
    var mostFrequent: String? = null

    for (word in words.keys.sorted()) {
        val count = words[word]!!
        if (count > frequency) {
            frequency = count
            mostFrequent = word
        }
    }

    println(mostFrequent)
}
