package `in`.kmp.demoapp.utils

interface LocalStorage {
    fun getString(key: String): String?
    fun putString(key: String, value: String)
    fun getBool(key: String): Boolean?
    fun putBool(key: String, value: Boolean)
    fun remove(key: String)
    fun clear()
}