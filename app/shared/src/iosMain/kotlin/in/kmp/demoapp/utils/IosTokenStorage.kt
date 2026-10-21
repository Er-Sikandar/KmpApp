package `in`.kmp.demoapp.utils

import `in`.kmp.demoapp.networks.LocalStorage
import platform.Foundation.*

class IosTokenStorage : LocalStorage {
    private val suite = "app_prefs"
    private val defaults = NSUserDefaults(suiteName = suite)
/*
    private val defaults = NSUserDefaults.standardUserDefaults
*/

    override fun getString(key: String) = defaults.stringForKey(key)
    override fun putString(key: String, value: String) { defaults.setObject(value, key) }
    override fun getBool(key: String) = defaults.boolForKey(key)

    override fun putBool(key: String, value: Boolean) { defaults.setBool(value, key) }

    override fun remove(key: String) { defaults.removeObjectForKey(key) }
    override fun clear() {
        defaults.removePersistentDomainForName(suite)
    }
}