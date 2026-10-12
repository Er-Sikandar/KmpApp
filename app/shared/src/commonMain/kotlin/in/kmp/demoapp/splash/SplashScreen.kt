package `in`.kmp.demoapp.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import `in`.kmp.demoapp.App
import `in`.kmp.demoapp.Greeting
import kmpdemoapp.app.shared.generated.resources.Res
import kmpdemoapp.app.shared.generated.resources.compose_multiplatform
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Duration.Companion.milliseconds

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        navLogin = {},
        navHome = {}
    )
}

@Composable
fun SplashScreen(navLogin: () -> Unit, navHome: () -> Unit){
    var showContent by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        coroutineScope.launch {
            delay(3000.milliseconds)
            navLogin()
        }
    }
    Scaffold { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(showContent,
                    enter = fadeIn(
                        animationSpec = tween(durationMillis = 2000)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(durationMillis = 2000)
                    )) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Welcome in KMP $greeting")
                    }
                }
            }
        }
    }
}