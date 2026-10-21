package `in`.kmp.demoapp.utils

import android.content.Context
import android.content.SharedPreferences
import `in`.kmp.demoapp.networks.LocalStorage
import androidx.core.content.edit

class AndroidTokenStorage(ctx: Context) : LocalStorage {
    /*private val prefs = EncryptedSharedPreferences.create(ctx, "secure_kmp_prefs",
        MasterKey.Builder(ctx).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )*/
    private val prefs: SharedPreferences = ctx.getSharedPreferences("secure_kmp_prefs", Context.MODE_PRIVATE)

    override fun getString(key: String) = prefs.getString(key, null)
    override fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }

    override fun getBool(key: String)=prefs.getBoolean(key, false)

    override fun putBool(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value) }
    }

    override fun remove(key: String) {
        prefs.edit { remove(key) }
    }
    override fun clear() { prefs.edit { clear() } }



}