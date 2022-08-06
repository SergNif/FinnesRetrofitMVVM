package com.bignerdranch.android.finnesretrofitmvvm.data.storage

import com.bignerdranch.android.finnesretrofitmvvm.domain.models.data.DataPage3

fun main(args: Array<String>) {
    var a = MyVariable()
    Singleton.printName()
}

open class MyVariable {

    open fun printName() {
        print("This is class MyVariable")
    }

    init {
        println("Class init method.")
    }
}

object Singleton : MyVariable() {

    init {
        println("Singleton class invoked.")
    }

    var name = "Kotlin Objects"
    override fun printName() {
        println(name)
    }
}

