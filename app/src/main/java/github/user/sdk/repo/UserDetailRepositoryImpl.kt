package github.user.sdk.repo

import github.user.sdk.api.ApiService
import github.user.sdk.api.HttpHandler
import github.user.sdk.api.HttpResult
import github.user.sdk.data.UserDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailRepositoryImpl @Inject constructor(private val service: ApiService) :
    UserDetailRepository {

    private val httpHandler = HttpHandler()
    override suspend fun getUserDetail(username: String): Flow<UserDetailResponse> {
        val result = httpHandler.getResult {
            service.queryUserDetail(username)
        }
        return when (result) {
            is HttpResult.Success -> flow { emit(result.data) }
            is HttpResult.Error -> throw result.exception
        }
    }
}