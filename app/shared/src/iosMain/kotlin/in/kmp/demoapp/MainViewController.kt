package `in`.kmp.demoapp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import `in`.kmp.demoapp.modules.AppContainer
import `in`.kmp.demoapp.utils.IosToastManager
import `in`.kmp.demoapp.utils.IosTokenStorage
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController {
    val toast = remember { IosToastManager() }
    val container = remember { AppContainer(Darwin.create(), IosTokenStorage(), toast) }
    App(container, toast)
}