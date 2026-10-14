package `in`.kmp.demoapp.kmp

import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarRightAction(
    val icon: ImageVector,
    val contentDescription: String? = null,
    val onClick: () -> Unit
)
