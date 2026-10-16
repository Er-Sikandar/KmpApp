package `in`.kmp.demoapp.reglogin.data

import `in`.kmp.demoapp.networks.ApiResult
import `in`.kmp.demoapp.networks.apiPostForm
import io.ktor.client.HttpClient

class AuthApi(private val client: HttpClient) {
    suspend fun loginApi(params: Map<String, String>): ApiResult<UserData?> =
        client.apiPostForm("/auth/login", params = params)

    suspend fun reg(paramPost: Map<String, String>): ApiResult<Boolean?> =
        client.apiPostForm("/auth/register", params =paramPost)


}