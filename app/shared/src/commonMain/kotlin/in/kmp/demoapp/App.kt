package `in`.kmp.demoapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import `in`.kmp.demoapp.utils.KmpNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        KmpNavGraph(
            navController = navController
        )
    }
}