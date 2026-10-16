package `in`.kmp.demoapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import `in`.kmp.demoapp.AndroidTokenStorage
import `in`.kmp.demoapp.modules.AppContainer
import `in`.kmp.demoapp.networks.LocalStorage
import io.ktor.client.engine.okhttp.OkHttp
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideLocalStorage(@ApplicationContext context: Context): LocalStorage =
        AndroidTokenStorage(context)

    @Provides @Singleton
    fun provideAppContainer(localStorage: LocalStorage): AppContainer =
        AppContainer(OkHttp.create(), localStorage)
}