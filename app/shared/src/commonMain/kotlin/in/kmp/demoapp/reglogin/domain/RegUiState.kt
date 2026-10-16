package `in`.kmp.demoapp.reglogin.domain

data class RegUiState(
    val fullNane: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val nameError: String? = null,
    val phoneError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val registered: Boolean = false
)
