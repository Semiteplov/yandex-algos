package sprint1

// https://contest.yandex.ru/contest/22449/problems/H/
fun main() {
    val firstNumber = readln()
    val secondNumber = readln()
    println(getSum(firstNumber, secondNumber))
}

private fun getSum(firstNumber: String, secondNumber: String): String {
    val length = if (firstNumber.length >= secondNumber.length) secondNumber.length else firstNumber.length
    val arr = mutableListOf<Int>()
    var add = 0
    for (i in length - 1 downTo 0) {
        if (firstNumber[length - 1 - i].toString().toInt() + secondNumber[length - 1 - i].toString().toInt() + add > 1) {
            arr.add(0, 0)
            add = 1
        } else {
            arr.add(0, firstNumber[length - 1 - i].toString().toInt() + secondNumber[length - 1 - i].toString().toInt() + add)
            add = 0
        }
    }
    if (add > 0) arr.add(0, add)
    if (firstNumber.length > secondNumber.length) {
        arr.add(0, firstNumber.take(secondNumber.length).toInt())
    } else if(secondNumber.length > firstNumber.length) {
        arr.add(0, firstNumber.take(firstNumber.length).toInt())
    }
    return arr.joinToString("")
}