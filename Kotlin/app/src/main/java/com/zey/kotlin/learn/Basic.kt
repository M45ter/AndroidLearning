package com.zey.kotlin.learn

//入口函数，直接写main函数，实际上会生成一个文件名为名字的类BasicKt.class
fun main() {
//    println("Hello World!")
//    variable()
//    stringTemplate()
//    forTest()
    whileTest()
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

//将表达式作为函数体、返回值类型自动推断的函数
fun sum1(a: Int, b: Int) = a + b

//无返回函数 Unit类似java void
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

//Unit可以省略
fun printSum1(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun variable() {
    //定义只读局部变量使用关键字 val 定义。只能为其赋值一次。
    val a: Int = 1  // 立即赋值
    val b = 2   // 自动推断出 `Int` 类型
    val c: Int  // 如果没有初始值类型不能省略
    //    a+c c未初始化，报错编译不通过
    c = 3       // 明确赋值
    println("a+c=" + (a + c))

    //可重新赋值的变量使用 var 关键字
    var x = 5 // 自动推断出 `Int` 类型
    x += 1
}

// 这是一个行注释

/* 这是一个多行的
   块注释。 */

/* 注释从这里开始
/* 包含嵌套的注释 */
并且在这里结束。 */

fun stringTemplate() {
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

//和maxOf效果一样，使用if表达式 类似java (a+b)?a:b 三元运算
fun maxOf1(a: Int, b: Int) = if (a > b) a else b

//is 运算符检测一个表达式是否某类型的一个实例。 如果一个不可变的局部变量或属性已经判断出为某类型，
//那么检测后的分支中可以直接当作该类型使用，无需显式转换
//可为空的后面加?
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
    }

    // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    return null
}

fun getStringLength1(obj: Any): Int? {
    if (obj !is String) return null

    // `obj` 在这一分支自动转换为 `String`
    return obj.length
}

//实际在is以后自动强制转换
fun getStringLength2(obj: Any): Int? {
    // `obj` 在 `&&` 右边自动转换成 `String` 类型
    if (obj is String && obj.length > 0) {
        return obj.length
    }

    return null
}

fun forTest() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

fun whileTest() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

//when表达式看着有点像if else
fun describe(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        !is String -> "Not a string"
        else       -> "Unknown"
    }

//使用 in 运算符来检测某个数字是否在指定区间内
fun inTest() {
    val x = 10
    val y = 9
    if (x in 1..y+1) {
        println("fits in range")
    }

    val list = listOf("a", "b", "c")

    //判断区间外
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    //list.indices -> 0..size-1
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }
}