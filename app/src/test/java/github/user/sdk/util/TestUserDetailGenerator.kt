package github.user.sdk.util

import com.google.gson.Gson
import github.user.sdk.data.UserDetailResponse
import java.io.File

class TestUserDetailGenerator {
    private var user = UserDetailResponse()

    init {
        val gson = Gson()
        val jsonString = getJson("single_user_response.json")
        gson.fromJson(jsonString, UserDetailResponse::class.java)?.let {
            user = it
        }
    }

    fun generateUser(): UserDetailResponse {
        return user
    }

    private fun getJson(path: String): String {
        val uri = this.javaClass.classLoader?.getResource(path)
        return uri?.let { url ->
            val file = File(url.path)
            String(file.readBytes())
        } ?: ""
    }
}