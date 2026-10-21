package `in`.kmp.demoapp.utils

import android.app.Application
import android.content.Context
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    companion object {
        private lateinit var appContext: Context
        fun getAppContext(): Context {
            return appContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        appContext = this
        Log.e("BaseApp","onCreate()")
    }
    override fun onTerminate() {
        Log.e("BaseApp", "onTerminate()")
        super.onTerminate()
    }
}