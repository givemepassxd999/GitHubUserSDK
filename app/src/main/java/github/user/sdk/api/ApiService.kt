package github.user.sdk.api

import github.user.sdk.data.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun queryUsers(@Query("per_page") perPage: Int = 100): List<ItemResponse>
}