package github.user.sdk.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login") var login: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
)