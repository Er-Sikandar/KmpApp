package `in`.kmp.demoapp.reglogin.domain

sealed class LoginEvent {
    data class EmailChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    data object Submit : LoginEvent()
    data object IsLoginFalse : LoginEvent()
}