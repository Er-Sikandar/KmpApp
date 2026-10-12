package `in`.kmp.demoapp.reglogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import `in`.kmp.demoapp.theme.colorWhite
import `in`.kmp.demoapp.theme.spacingSmall

@Composable
fun LoginScreen(navSignUp: () -> Unit,navHome:()-> Unit){
    Scaffold { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    navSignUp()
                }){
                    Text("Go SignUp", color =colorWhite)
                }
                Spacer(modifier = Modifier.height(spacingSmall))
                Button(onClick = {
                    navHome()
                }){
                    Text("Go Home", color = colorWhite)
                }
            }
        }
    }
}