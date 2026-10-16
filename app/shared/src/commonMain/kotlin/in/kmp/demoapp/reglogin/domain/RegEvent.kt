package `in`.kmp.demoapp.reglogin.domain

sealed class RegEvent {
    data class FullNameChanged(val value: String) : RegEvent()
    data class PhoneChanged(val value: String) : RegEvent()
    data class EmailChanged(val value: String) : RegEvent()
    data class PasswordChanged(val value: String) : RegEvent()
    data object Submit : RegEvent()
    data object IsRegFalse : RegEvent()
}