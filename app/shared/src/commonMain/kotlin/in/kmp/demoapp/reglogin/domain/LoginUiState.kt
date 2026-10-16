package `in`.kmp.demoapp.reglogin.domain

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false
)