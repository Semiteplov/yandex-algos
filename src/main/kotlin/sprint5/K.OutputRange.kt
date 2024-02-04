package sprint5

import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter

// <template>
class NodeK(var left: NodeK?, var right: NodeK?, var value: Int)
// <template>

@Throws(IOException::class)
fun printRange(root: NodeK?, L: Int, R: Int, writer: BufferedWriter) {
    val res = mutableListOf<Int>()
    dfs(root, L, R, res)
    res.forEach { writer.write("$it\n") }
}

fun dfs(root: NodeK?, L: Int, R: Int, list: MutableList<Int>) {
    if (root == null) return

    if (root.value >= L) {
        dfs(root.left, L, R, list)
    }

    if (root.value in L..R) {
        list += root.value
    }

    if (root.value <= R) {
        dfs(root.right, L, R, list)
    }
}

fun testK() {
    val node1 = NodeK(null, null, 2)
    val node2 = NodeK(null, node1, 1)
    val node3 = NodeK(null, null, 8)
    val node4 = NodeK(null, node3, 8)
    val node5 = NodeK(node4, null, 9)
    val node6 = NodeK(node5, null, 10)
    val node7 = NodeK(node2, node6, 5)
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    printRange(node7, 2, 8, writer)
    writer.flush()
    // expected output: 2 5 8 8
}