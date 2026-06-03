package `in`.kmp.demoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform