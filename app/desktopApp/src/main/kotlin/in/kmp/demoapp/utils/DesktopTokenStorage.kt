package `in`.kmp.demoapp.utils

import `in`.kmp.demoapp.networks.LocalStorage
import java.util.prefs.Preferences

class DesktopTokenStorage : LocalStorage {
    private val prefs = Preferences.userRoot().node("in/kmp/demoapp")

    override fun getString(key: String): String? = prefs.get(key, null)

    override fun putString(key: String, value: String) {
        prefs.put(key, value)
        prefs.flush()
    }

    override fun getBool(key: String): Boolean = prefs.getBoolean(key, false)

    override fun putBool(key: String, value: Boolean) {
        prefs.putBoolean(key, value)
        prefs.flush()
    }

    override fun remove(key: String) {
        prefs.remove(key)
        prefs.flush()
    }

    override fun clear() {
        prefs.clear()
        prefs.flush()
    }
}