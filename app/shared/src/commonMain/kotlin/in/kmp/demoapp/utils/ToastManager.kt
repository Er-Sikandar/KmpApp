package `in`.kmp.demoapp.utils

import androidx.compose.runtime.staticCompositionLocalOf

interface ToastManager {
    fun show(message: String)
}

val LocalToast = staticCompositionLocalOf<ToastManager> { error("ToastManager not provided") }