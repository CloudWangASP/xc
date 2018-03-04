package com.cloud.xc.kotlin

/**
 * Created by cloud on 2018/2/6.
 */
class TestKotlin {


}

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base) : Base by b

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
}

fun test() {
    println("asd"::class)

    var res = when {
        5 in 1..10 -> "hello"
        else -> ""
    }
}