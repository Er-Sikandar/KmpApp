package `in`.kmp.demoapp.reglogin.data

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: String ?=null,
    val name: String ?=null,
    val phone: String ?=null,
    val email: String ?=null,
    val password: String? =null,
    val token: String? = null,
    val refresh_token: String? = null,
)
