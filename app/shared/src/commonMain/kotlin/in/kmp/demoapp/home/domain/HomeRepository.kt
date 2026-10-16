package `in`.kmp.demoapp.home.domain

import `in`.kmp.demoapp.networks.ApiResult

interface HomeRepository  {
    suspend fun userExist(userId: String): ApiResult<Boolean?>

}