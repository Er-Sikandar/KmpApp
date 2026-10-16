package `in`.kmp.demoapp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import `in`.kmp.demoapp.modules.AppContainer
import io.ktor.client.engine.cio.CIO

fun main() = application {
    val container = remember {
        AppContainer(CIO.create(), DesktopTokenStorage())
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpDemoApp",
    ) {
        App(container)
    }
}