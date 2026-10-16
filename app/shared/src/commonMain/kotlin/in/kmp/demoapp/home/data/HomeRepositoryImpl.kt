package `in`.kmp.demoapp.home.data

import `in`.kmp.demoapp.home.domain.HomeRepository
import `in`.kmp.demoapp.networks.ApiResult

class HomeRepositoryImpl (private val api: HomeApi) : HomeRepository {

    override suspend fun userExist(userId: String): ApiResult<Boolean?> {
        TODO("Not yet implemented")
    }


}