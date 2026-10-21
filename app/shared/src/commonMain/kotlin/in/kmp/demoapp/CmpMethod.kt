package `in`.kmp.demoapp

class CmpMethod {
    private val platform = getPlatform()

    fun greet(): String {
        return sayHello(platform.name)
    }


}