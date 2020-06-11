package com.zey.kotlin.learn

class MyClass(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    lateinit var color: String

    init {
        println("Second initializer block that prints ${name.length}")
        color = "red"
        println("Second initializer block that prints $color")
    }

    constructor() : this("123") {
        println("次构造函数")
    }
}

fun main() {
    MyClass()
}
