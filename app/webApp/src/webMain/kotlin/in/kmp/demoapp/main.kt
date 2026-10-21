package `in`.kmp.demoapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import `in`.kmp.demoapp.modules.AppContainer
import `in`.kmp.demoapp.utils.WebToastManager
import `in`.kmp.demoapp.utils.WebTokenStorage
import io.ktor.client.engine.js.Js

/**
 * Error solve cmd Only Web(js):
 * ./gradlew kotlinStoreYarnLock
 * ./gradlew kotlinUpgradeYarnLock
 * Error solve cmd Only Web(js):
 * ./gradlew kotlinWasmStoreYarnLock
 *./gradlew kotlinWasmUpgradeYarnLock
 */

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val toast = WebToastManager()
    val container = AppContainer(Js.create(), WebTokenStorage(),toast)
    ComposeViewport {
        App(container,toast)
    }
}