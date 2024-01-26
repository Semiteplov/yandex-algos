package sprint4

fun main() {
    val reader = System.`in`.bufferedReader()
    val s = reader.readLine()!!
    val t = reader.readLine()!!

    println(if (areStringsEqual(s, t)) "YES" else "NO")
}

fun areStringsEqual(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val mapS = HashMap<Char, Char>()
    val mapT = HashMap<Char, Char>()

    for (i in s.indices) {
        val charS = s[i]
        val charT = t[i]

        if (!mapS.containsKey(charS)) mapS[charS] = charT
        if (!mapT.containsKey(charT)) mapT[charT] = charS

        if (mapS[charS] != charT || mapT[charT] != charS) return false
    }

    return true
}