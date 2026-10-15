package `in`.kmp.demoapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

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
    ComposeViewport {
        App()
    }
}