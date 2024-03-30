package github.user.sdk.repo

import github.user.sdk.data.UserDetailResponse
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    suspend fun getUserDetail(username: String): Flow<UserDetailResponse>
}