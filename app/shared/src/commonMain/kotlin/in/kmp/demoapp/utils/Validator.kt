package `in`.kmp.demoapp.utils

object Validator {
    private val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun fulNameError(name: String): String? = when {
        name.isBlank() -> "Full Name is required"
        else -> null
    }
    fun phoneError(phone: String): String? = when {
        phone.isBlank() -> "Phone No is required"
        phone.length <10 -> "Minimum 10 digits"
        else -> null
    }
    fun emailError(email: String): String? = when {
        email.isBlank() -> "Email is required"
        !emailRegex.matches(email.trim()) -> "Enter a valid email"
        else -> null
    }
    fun passwordError(password: String): String? = when {
        password.isBlank() -> "Password is required"
        password.length < 6 -> "Minimum 6 characters"
        else -> null
    }


}