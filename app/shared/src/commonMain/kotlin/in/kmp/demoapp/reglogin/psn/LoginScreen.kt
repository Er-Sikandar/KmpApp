package `in`.kmp.demoapp.reglogin.psn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import `in`.kmp.demoapp.notifications.psn.NotyViewModel
import `in`.kmp.demoapp.reglogin.domain.LoginEvent
import `in`.kmp.demoapp.theme.colorWhite
import `in`.kmp.demoapp.theme.lightPrimary
import `in`.kmp.demoapp.theme.spacingLarge
import `in`.kmp.demoapp.theme.spacingSmall
import `in`.kmp.demoapp.utils.LocalToast

@Composable
fun LoginScreen(navSignUp: () -> Unit,navHome:()-> Unit, vm: RegLogViewModel){
    var showPassword by remember { mutableStateOf(false) }
    val state by vm.state.collectAsState()
    val currentNavHome by rememberUpdatedState(navHome)
    val toast = LocalToast.current


    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            vm.onLoginEvent(LoginEvent.IsLoginFalse)
            currentNavHome()
        }
    }

    Scaffold { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize().padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Welcome back", style = MaterialTheme.typography.headlineMedium)
                Text("Sign in to continue", style = MaterialTheme.typography.bodyMedium)
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { vm.onLoginEvent(LoginEvent.EmailChanged(it)) },
                    label = { Text("Email") },
                    singleLine = true,
                    isError = state.emailError != null,
                    supportingText = { state.emailError?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { vm.onLoginEvent(LoginEvent.PasswordChanged(it)) },
                    label = { Text("Password") },
                    singleLine = true,
                    isError = state.passwordError != null,
                    supportingText = { state.passwordError?.let { Text(it) } },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(imageVector = if (showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = if (showPassword) "Hide password" else "Show password"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { vm.onLoginEvent(LoginEvent.Submit) }),
                    modifier = Modifier.fillMaxWidth()
                )
                state.error?.let {
                    Text(it, color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(spacingLarge))
                Button(
                    onClick = { vm.onLoginEvent(LoginEvent.Submit) },
                    enabled = !state.isLoading,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            Modifier.size(20.dp), strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Login")
                    }
                }
                Spacer(modifier = Modifier.height(spacingSmall))
                Text("SignUp Here", color = lightPrimary, modifier = Modifier.padding(5.dp).clickable{
                   // navSignUp()
                    toast.show("SignUp Here..")
                })
            }
        }
    }
}