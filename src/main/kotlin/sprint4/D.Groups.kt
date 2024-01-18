package sprint4

// https://contest.yandex.ru/contest/23991/problems/D/
fun main() {
    val n = readln().toInt()
    val set = LinkedHashSet<String>() // чтобы сохранить порядок
    repeat(n) {
        val s = readln()
        set.add(s)
    }
    for (group in set) {
        println(group)
    }
}
