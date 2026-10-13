package `in`.kmp.demoapp

import android.app.Application
import android.util.Log

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.e("BaseApp","onCreate")
      //  PlayerServiceLocator.init(PlatformContext(applicationContext))
    }
}