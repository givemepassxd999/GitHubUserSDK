package github.user.sdk.repo

import github.user.sdk.data.ItemResponse

interface Repository {
    suspend fun queryUsers(): List<ItemResponse>
}