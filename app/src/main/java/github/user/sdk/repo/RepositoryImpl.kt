package github.user.sdk.repo

import github.user.sdk.api.ApiService
import github.user.sdk.data.ItemResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: ApiService) : Repository {
    override suspend fun queryUsers(): List<ItemResponse> {
        return service.queryUsers()
    }
}