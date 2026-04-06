package com.exle.kmpapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform