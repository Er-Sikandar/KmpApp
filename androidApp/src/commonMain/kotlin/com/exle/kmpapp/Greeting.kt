package com.exle.kmpapp

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, Kmp: ${platform.name}!"
    }
}