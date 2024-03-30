package github.user.sdk.api

import github.user.sdk.data.UserDetailResponse
import github.user.sdk.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun queryUsers(@Query("per_page") perPage: Int = 100): Response<List<UserResponse>>

    @GET("/users/{username}")
    suspend fun queryUserDetail(@Path("username") username: String): Response<UserDetailResponse>
}