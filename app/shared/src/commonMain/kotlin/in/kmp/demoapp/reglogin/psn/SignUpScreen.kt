package `in`.kmp.demoapp.reglogin.psn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import `in`.kmp.demoapp.reglogin.domain.RegEvent
import `in`.kmp.demoapp.theme.spacingLarge

@Composable
fun SignUpScreen(onBack:()-> Unit,vm: RegLogViewModel){
    var showPassword by remember { mutableStateOf(false) }
    val state by vm.regState.collectAsState()
    val currentNavBack by rememberUpdatedState(onBack)
    LaunchedEffect(state.registered) {
        if (state.registered) {
            vm.onRegEvent(RegEvent.IsRegFalse)
            currentNavBack()
        }
    }

    Scaffold { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize().padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Create Account", style = MaterialTheme.typography.headlineMedium)
                Text("Sign up to get started", style = MaterialTheme.typography.bodyMedium)
                OutlinedTextField(
                    value = state.fullNane,
                    onValueChange = { vm.onRegEvent(RegEvent.FullNameChanged(it)) },
                    label = { Text("Full Name") },
                    singleLine = true,
                    isError = state.nameError != null,
                    supportingText = { state.nameError?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = state.phone,
                    onValueChange = { input ->
                        val phone = input.filter { it.isDigit() }.take(10)
                        vm.onRegEvent(RegEvent.PhoneChanged(phone))
                        },
                    label = { Text("Phone No") },
                    singleLine = true,
                    isError = state.phoneError != null,
                    supportingText = { state.phoneError?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { vm.onRegEvent(RegEvent.EmailChanged(it)) },
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
                    onValueChange = { vm.onRegEvent(RegEvent.PasswordChanged(it)) },
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
                    keyboardActions = KeyboardActions(onDone = { vm.onRegEvent(RegEvent.Submit) }),
                    modifier = Modifier.fillMaxWidth()
                )
                state.error?.let {
                    Text(it, color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(spacingLarge))
                Button(
                    onClick = { vm.onRegEvent(RegEvent.Submit) },
                    enabled = !state.isLoading,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            Modifier.size(20.dp), strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("SignUp")
                    }
                }

            }
        }
    }
}