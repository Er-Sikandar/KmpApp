package `in`.kmp.demoapp.utils

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLElement

@OptIn(ExperimentalWasmJsInterop::class)
class WebToastManager : ToastManager {
    override fun show(message: String) {
        val body = document.body ?: return
        val toast = document.createElement("div") as HTMLElement
        toast.textContent = message
        toast.setAttribute(
            "style",
            "position:fixed;bottom:48px;left:50%;transform:translateX(-50%);" +
                    "background:#323232;color:#fff;padding:12px 24px;border-radius:8px;" +
                    "font-family:sans-serif;font-size:14px;z-index:9999;" +
                    "opacity:0;transition:opacity .25s;"
        )
        body.appendChild(toast)

        // fade in
        window.setTimeout({ toast.style.opacity = "1"; null }, 10)
        // fade out, then remove
        window.setTimeout({ toast.style.opacity = "0"; null }, 2000)
        window.setTimeout({ toast.remove(); null }, 2300)
    }
}