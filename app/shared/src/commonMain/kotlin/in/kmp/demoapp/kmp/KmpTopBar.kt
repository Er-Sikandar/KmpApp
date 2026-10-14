package `in`.kmp.demoapp.kmp

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications


@Composable
fun KmpTopBar(title: String,
              modifier: Modifier = Modifier,
              onNavigationClick: () -> Unit,
              actions: List<TopBarRightAction> = emptyList()){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back_Icon"
                    )
                }
        },
       /* actions = {
            actions.forEach { action ->
                IconButton(onClick = action.onClick) {
                    Icon(
                        imageVector = action.icon,
                        tint = tint,
                        contentDescription = action.contentDescription
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()*/
    )
}