package `in`.kmp.demoapp.modules

import androidx.compose.runtime.staticCompositionLocalOf
import `in`.kmp.demoapp.networks.AuthApi
import `in`.kmp.demoapp.networks.LocalStorage
import `in`.kmp.demoapp.networks.createHttpClient
import `in`.kmp.demoapp.reglogin.data.AuthRepositoryImpl
import `in`.kmp.demoapp.reglogin.domain.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine

class AppContainer(engine: HttpClientEngine, localStorage: LocalStorage) {
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

}