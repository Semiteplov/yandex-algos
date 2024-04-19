package sprint8

// https://contest.yandex.ru/contest/26131/problems/B
fun isPassAllowed(passportName: String, databaseName: String): Boolean {
    var passportIndex = 0
    var databaseIndex = 0
    var diffCount = 0

    while (passportIndex < passportName.length && databaseIndex < databaseName.length) {
        if (passportName[passportIndex] != databaseName[databaseIndex]) {
            diffCount++
            if (diffCount > 1) return false // Более одного различия, прекращаем проверку
            if (passportName.length > databaseName.length) {
                passportIndex++ // Переходим к следующему символу в имени из паспорта
            } else if (passportName.length < databaseName.length) {
                databaseIndex++ // Переходим к следующему символу в имени из базы
            } else {
                passportIndex++
                databaseIndex++
            }
        } else {
            passportIndex++
            databaseIndex++
        }
    }

    // Проверяем, остались ли символы в конце одной из строк
    if (passportIndex < passportName.length || databaseIndex < databaseName.length) {
        diffCount++
    }

    return diffCount <= 1
}

fun main() {
    val passportName = readln()
    val databaseName = readln()

    val result = if (isPassAllowed(passportName, databaseName)) "OK" else "FAIL"
    println(result)
}
