package `in`.kmp.demoapp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import `in`.kmp.demoapp.modules.AppContainer
import `in`.kmp.demoapp.utils.DesktopToastManager
import `in`.kmp.demoapp.utils.DesktopTokenStorage
import io.ktor.client.engine.cio.CIO

fun main() = application {
    val toast = remember { DesktopToastManager() }
    val container = remember {
        AppContainer(CIO.create(), DesktopTokenStorage(), toast)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpDemoApp",
    ) {
        App(container,toast = remember { DesktopToastManager() })
    }
}