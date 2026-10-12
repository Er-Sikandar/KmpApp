package `in`.kmp.demoapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import `in`.kmp.demoapp.theme.lightPrimary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.microseconds


@Composable
fun HomeScreen(navHomeToWeb: (String, Int) -> Unit,
               navHomeToLogin: () -> Unit,
               navHomeToNoty: () -> Unit,
               onExitApp: () -> Unit){
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var doubleBackToExitPressedOnce by remember { mutableStateOf(false) }
    val topBarText = remember { mutableStateOf("New JetPack App") }


    LaunchedEffect(doubleBackToExitPressedOnce) {
        if (doubleBackToExitPressedOnce) {
            coroutineScope.launch {
                delay(2000.microseconds)
                doubleBackToExitPressedOnce = false
            }
        }
    }
    /*BackHandler {
        if (doubleBackToExitPressedOnce) {
            onExitApp()
        } else {
            doubleBackToExitPressedOnce = true
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }*/
    Scaffold ( topBar = {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = topBarText.value)
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        //drawerState.open()
                    }
                }) {
                    Icon(
                        Icons.Filled.Menu,
                        tint = lightPrimary,
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    navHomeToNoty()
                }) {
                    Icon(
                        Icons.Filled.Notifications,
                        tint = lightPrimary,
                        contentDescription = null
                    )
                }
            },
        )
    }){innerPadding->
        Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

            }
        }
    }
}