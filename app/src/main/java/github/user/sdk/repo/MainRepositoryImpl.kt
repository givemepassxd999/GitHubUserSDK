package github.user.sdk.repo

import github.user.sdk.api.ApiService
import github.user.sdk.api.HttpHandler
import github.user.sdk.api.HttpResult
import github.user.sdk.data.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val service: ApiService) : MainRepository {

    private val httpHandler = HttpHandler()
    override suspend fun queryUsers(): Flow<List<UserResponse>> {
        val result = httpHandler.getResult {
            service.queryUsers()
        }
        return when (result) {
            is HttpResult.Success -> flow { emit(result.data) }
            is HttpResult.Error -> throw result.exception
        }
    }
}