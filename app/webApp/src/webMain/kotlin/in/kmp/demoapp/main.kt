package `in`.kmp.demoapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

/**
 * Error solve cmd:
 * ./gradlew kotlinUpgradeYarnLock
 * ./gradlew :app:shared:jsBrowserDevelopmentRun
 *./gradlew kotlinWasmUpgradeYarnLock
 */

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App()
    }
}