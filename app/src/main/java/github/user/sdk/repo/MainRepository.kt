package github.user.sdk.repo

import github.user.sdk.data.UserResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun queryUsers(): Flow<List<UserResponse>>
}