import java.io.BufferedReader
import java.io.InputStreamReader

// https://contest.yandex.ru/contest/23638/problems/L/
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val n = reader.readLine().toInt()  // количество дней
    val savings = reader.readLine().split(' ').map { it.toInt() }  // накопления Васи за каждый день
    val s = reader.readLine().toInt()  // стоимость велосипеда

    // Оптимизированный рекурсивный бинарный поиск
    fun binarySearch(left: Int, right: Int, target: Int): Int {
        if (left > right) return -1  // если элемент не найден
        val mid = left + (right - left) / 2
        if (savings[mid] >= target) {
            // Проверяем, является ли текущий элемент первым, удовлетворяющим условиям
            if (mid == 0 || savings[mid - 1] < target) return mid + 1  // +1, так как дни начинаются с 1
            return binarySearch(left, mid - 1, target)  // Продолжаем поиск в левой части
        } else {
            return binarySearch(mid + 1, right, target)  // Продолжаем поиск в правой части
        }
    }

    // Поиск дней для покупки одного и двух велосипедов
    val dayForOneBike = binarySearch(0, n - 1, s)
    val dayForTwoBikes = if (dayForOneBike != -1) binarySearch(dayForOneBike - 1, n - 1, 2 * s) else -1

    // Вывод результатов
    println("$dayForOneBike $dayForTwoBikes")
}
