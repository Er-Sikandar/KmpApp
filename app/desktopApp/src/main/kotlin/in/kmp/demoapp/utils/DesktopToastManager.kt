package `in`.kmp.demoapp.utils

import java.awt.*
import javax.swing.*

class DesktopToastManager : ToastManager {
    override fun show(message: String) {
        SwingUtilities.invokeLater {
            val window = JWindow()
            val label = JLabel(message, SwingConstants.CENTER).apply {
                foreground = Color.WHITE
                border = BorderFactory.createEmptyBorder(12, 24, 12, 24)
            }
            window.contentPane.background = Color(50, 50, 50)
            window.contentPane.add(label)
            window.pack()
            window.isAlwaysOnTop = true
            val screen = Toolkit.getDefaultToolkit().screenSize
            window.setLocation((screen.width - window.width) / 2, screen.height - window.height - 80)
            window.isVisible = true

            Timer(2000) { window.dispose() }.apply { isRepeats = false; start() }
        }
    }
}