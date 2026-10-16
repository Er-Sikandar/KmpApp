package `in`.kmp.demoapp

import androidx.compose.ui.window.ComposeUIViewController
import `in`.kmp.demoapp.modules.AppContainer
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController { App(container) }
private val container: AppContainer by lazy {
    AppContainer(Darwin.create(), IosTokenStorage())
}