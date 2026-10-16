package `in`.kmp.demoapp.home.psn

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import `in`.kmp.demoapp.theme.colorWhite
import `in`.kmp.demoapp.theme.lightPrimary
import `in`.kmp.demoapp.theme.redColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.microseconds

@Composable
fun HomeScreen(homeViewModel: HomeViewModel,
    navHomeToWeb: (String, Int) -> Unit,
               navHomeToLogin: () -> Unit,
               navHomeToNoty: () -> Unit,
               onExitApp: () -> Unit){
    //val context:Context = LocalContext.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    var doubleBackToExitPressedOnce by remember { mutableStateOf(false) }
    val topBarText = remember { mutableStateOf("New JetPack App") }
    var showLogoutDialog by remember { mutableStateOf(false) }
    val isAccess by homeViewModel.isAccess.collectAsState()

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Logout", color = redColor, fontWeight = FontWeight.Medium) },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                Button(onClick = {
                    coroutineScope.launch {
                        showLogoutDialog = false
                        navHomeToLogin()
                    }
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = {
                    coroutineScope.launch {
                        showLogoutDialog = false
                    }
                }) {
                    Text("No")
                }
            }
        )
    }

    LaunchedEffect(doubleBackToExitPressedOnce) {
        if (doubleBackToExitPressedOnce) {
            coroutineScope.launch {
                delay(2000.microseconds)
                doubleBackToExitPressedOnce = false
            }
        }
    }
   /* BackHandler {
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
                Button(onClick = {
                    coroutineScope.launch {
                        showLogoutDialog=true
                    }
                }){
                    Text("Logout ${isAccess}", color = colorWhite)
                }
            }
        }
    }
}