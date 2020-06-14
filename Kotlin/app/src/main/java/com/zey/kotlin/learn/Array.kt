package com.zey.kotlin.learn

fun main() {
    val c0 = intArrayOf(1, 2, 3, 4, 5)
    println(c0.contentToString())
    val c1 = IntArray(5) { it }
    println(c1.contentToString())

    val d = arrayOf("Hello", "World")
    d[1] = "Kotlin"
    println(d.contentToString())

    c1.forEach { element -> println(element) }
    c1.forEachIndexed { index, i -> println("$index $i") }

    for (e in c1) {
        println("for in $e")
    }

    if (8 in c1) {
        println("if 8 in c1")
    }
    if (4 in c1) {
        println("if 4 in c1")
    }
}