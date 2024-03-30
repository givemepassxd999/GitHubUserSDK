package github.user.sdk.repo

import github.user.sdk.data.UserResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun queryUsers(): Flow<List<UserResponse>>
}