package `in`.kmp.demoapp.utils

import kotlinx.browser.localStorage

class WebTokenStorage : LocalStorage {

    override fun getString(key: String): String? = localStorage.getItem(key)

    override fun putString(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    override fun getBool(key: String): Boolean = localStorage.getItem(key) == "true"

    override fun putBool(key: String, value: Boolean) {
        localStorage.setItem(key, value.toString())
    }

    override fun remove(key: String) {
        localStorage.removeItem(key)
    }

    override fun clear() {
        localStorage.clear()
    }
}