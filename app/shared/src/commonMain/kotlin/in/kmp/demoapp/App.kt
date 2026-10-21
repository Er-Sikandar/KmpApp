package `in`.kmp.demoapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import `in`.kmp.demoapp.modules.AppContainer
import `in`.kmp.demoapp.modules.LocalAppContainer
import `in`.kmp.demoapp.utils.KmpNavGraph
import `in`.kmp.demoapp.utils.LocalToast
import `in`.kmp.demoapp.utils.ToastManager

@Composable
fun App(container: AppContainer, toast: ToastManager) {
    MaterialTheme {
        CompositionLocalProvider(LocalAppContainer provides container,  LocalToast provides toast) {
            val navController = rememberNavController()
            KmpNavGraph(navController)
        }
    }
}