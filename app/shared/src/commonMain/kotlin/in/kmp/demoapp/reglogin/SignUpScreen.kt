package `in`.kmp.demoapp.reglogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import `in`.kmp.demoapp.theme.colorWhite

@Composable
fun SignUpScreen(navLogin:()-> Unit){
    Scaffold { innerPadding ->

        Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
             Button(onClick = {
                 navLogin()
             }){
                 Text("Go Login", color = colorWhite)
             }
            }
        }
    }
}