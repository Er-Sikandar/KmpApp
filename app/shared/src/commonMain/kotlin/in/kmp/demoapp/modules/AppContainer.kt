package `in`.kmp.demoapp.modules

import androidx.compose.runtime.staticCompositionLocalOf
import `in`.kmp.demoapp.home.data.HomeApi
import `in`.kmp.demoapp.home.data.HomeRepositoryImpl
import `in`.kmp.demoapp.home.domain.HomeRepository
import `in`.kmp.demoapp.reglogin.data.AuthApi
import `in`.kmp.demoapp.utils.LocalStorage
import `in`.kmp.demoapp.networks.createHttpClient
import `in`.kmp.demoapp.reglogin.data.AuthRepositoryImpl
import `in`.kmp.demoapp.reglogin.domain.AuthRepository
import `in`.kmp.demoapp.utils.ToastManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine

class AppContainer(engine: HttpClientEngine, localStorage: LocalStorage,toast: ToastManager) {
    val storage = localStorage
    private val client = createHttpClient(engine, storage)
     val authModule = AuthModule(client)
     val homeModule = HomeModule(client)
}
val LocalAppContainer = staticCompositionLocalOf<AppContainer> {
    error("AppContainer not provided")
}
class AuthModule(client: HttpClient) {
    private val api = AuthApi(client)
    val authRepo: AuthRepository by lazy { AuthRepositoryImpl(api) }
}
class HomeModule(client: HttpClient) {
    private val api = HomeApi(client)
    val homeRepo: HomeRepository by lazy { HomeRepositoryImpl(api) }
}