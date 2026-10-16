package `in`.kmp.demoapp.reglogin.domain

import `in`.kmp.demoapp.reglogin.data.UserData
import `in`.kmp.demoapp.networks.ApiResult

interface AuthRepository {
    suspend fun login(email: String, password: String): ApiResult<UserData?>
    suspend fun reg(name: String,mob:String,email: String, password: String): ApiResult<Boolean?>
}