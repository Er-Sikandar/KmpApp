package `in`.kmp.demoapp

import android.app.Application

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
      //  PlayerServiceLocator.init(PlatformContext(applicationContext))
    }
}