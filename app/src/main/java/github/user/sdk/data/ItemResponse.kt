package github.user.sdk.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("login") var login: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
    @SerializedName("site_admin") val siteAdmin: Boolean? = null,
) : Parcelable

@Parcelize
data class UserDetailResponse(
    @SerializedName("avatar_url") val avatarUrl: String? = null,
    @SerializedName("name") var fullName: String? = null,
    @SerializedName("bio") val bio: String? = null,
    @SerializedName("login") var login: String? = null,
    @SerializedName("site_admin") val siteAdmin: Boolean? = null,
    @SerializedName("location") val location: String? = null,
    @SerializedName("blog") val blog: String? = null,
) : Parcelable