package `in`.kmp.demoapp.reglogin.data

import `in`.kmp.demoapp.networks.ApiResult
import `in`.kmp.demoapp.reglogin.domain.AuthRepository

class AuthRepositoryImpl (private val api: AuthApi) : AuthRepository {
    override suspend fun login(email: String, password: String): ApiResult<UserData?> =
        api.loginApi(mapOf("email" to email, "password" to password))

    override suspend fun reg(name: String,mob:String, email: String, password: String): ApiResult<Boolean?> =
        api.reg(mapOf("name" to name, "mobile" to mob, "email" to email, "password" to password)
        )

}