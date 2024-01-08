package sprint3

// https://contest.yandex.ru/contest/23638/problems/C/
fun main() {
    val str1 = readln()
    val str2 = readln()

    fun isSubsequence(): Boolean {
        var str1Index = 0
        var str2Index = 0

        while (str1Index < str1.length && str2Index < str2.length) {
            if (str1[str1Index] == str2[str2Index]) {
                str1Index++
            }
            str2Index++
        }

        return str1Index == str1.length
    }

    print(if (isSubsequence()) "True" else "False")
}
