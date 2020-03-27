package com.zey.kotlin.learn

import java.math.BigDecimal

//习惯用法
//创建 DTOs（POJOs/POCOs）
/**
 * 会为 Customer 类提供以下功能：
 *所有属性的 getters （对于 var 定义的还有 setters）
 *equals()
 *hashCode()
 *toString()
 *copy()
 *所有属性的 component1()、 component2()……等等
 */
data class Customer(val name: String, val email: String)

//函数的默认参数
fun foo(a: Int = 0, b: String = "") {}

fun main() {
    val list = listOf(0, 1, 2, 3, 4, 5)
    //过滤list
    val positives = list.filter { x -> x > 0 }
    //更短的写法
    val positives1 = list.filter { it > 0 }

    //检测元素是否存在于集合中
    if (1 in list) {
    }
    if (-1 !in list) {
    }

    val name = "Alex"
    //字符串内插 模版
    println("Name $name")

    //类型判断
    /*
    when (x) {
        is Foo //-> ……
        is Bar //-> ……
        else   //-> ……
    }
     */

    //遍历 map/pair型list
    /*
    for ((k, v) in map) {
        println("$k -> $v")
    }
     */

    //使用区间
    println("-------i in 1..100-------")
    for (i in 1..100) {
        println(i)
    }  // 闭区间：包含 100
    println("-------i in 1 until 100-------")
    for (i in 1 until 100) {
        println(i)
    } // 半开区间：不包含 100
    println("------- in 2..10 step 2-------")
    for (x in 2..10 step 2) {
        println(x)
    } // 步长2
    println("-------x in 10 downTo 1-------")
    for (x in 10 downTo 1) {
        println(x)
    } //倒过来10 ，9..1
    val x = 5
    if (x in 1..10) {
        println("x in 1..10")
    }

    //只读list
    val list1 = listOf("a", "b", "c")

    //只读map
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)

    //访问map
//    println(map["key"])
//    map["key"] = value

    "Convert this to camelcase".spaceToCamelCase()

    println("Resource.name = ${Resource.name}")

    //If not null 缩写
    println(list?.size)

    //If not null and else 缩写
    println(list?.size ?: "empty")

    //在可能会空的集合中取第一元素
    val element = list.firstOrNull()

    val value = element
    //if not null 执行代码
    value?.let{
        println("let in $it")
    }

    test()

    //对一个对象实例调用多个方法 （with）
    /*
    class Turtle {
        fun penDown()
        fun penUp()
        fun turn(degrees: Double)
        fun forward(pixels: Double)
    }

    val myTurtle = Turtle()
    with(myTurtle) { // 画一个 100 像素的正方形
        penDown()
        for(i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
     */

    //配置对象的属性（apply）
    /*
    val myRectangle = Rectangle().apply {
        length = 4
        breadth = 5
        color = 0xFAFAFA
    }
     */

    //使用可空布尔
    /*
    val b: Boolean? = null
    if (b == true) {
        //
    } else {
        // `b` 是 false 或者 null
    }
     */

    //交换两个变量
    var a = 1
    var b = 2
    a = b.also { b = a }
}

//延迟属性
val p: String by lazy {
    println("延迟属性")
    "lazy string p"
}

//扩展函数 从class文件看是把对象从参数传进去
fun String.spaceToCamelCase() {
    println("$this String扩展函数")
}

//创建单例
object Resource {
    val name = "Name"
}

//“try/catch”表达式
fun test() {
    val result = try {
        count()
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
    // 使用 result
    println("test result is $result")
}

fun count(): Int {
    return 10
}

//TODO()：将代码标记为不完整
fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")
