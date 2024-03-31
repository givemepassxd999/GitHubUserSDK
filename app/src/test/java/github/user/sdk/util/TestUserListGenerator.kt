package com.util

import com.google.gson.Gson
import github.user.sdk.data.UserResponse
import java.io.File


/**
 * Created by AhmedEltaher
 */

class TestUserListGenerator {

    private var users = arrayListOf(UserResponse())

    init {
        val gson = Gson()
        val jsonString = getJson("users_response.json")
        gson.fromJson<List<UserResponse>>(jsonString, UserResponse::class.java)?.let {
            users = ArrayList(it)
        }
    }

    fun generateUsers(): List<UserResponse> {
        return users
    }

    fun generateAUser(): UserResponse {
        return users[0]
    }

    private fun getJson(path: String): String {
        val uri = this.javaClass.classLoader?.getResource(path)
        return uri?.let { url ->
            val file = File(url.path)
            String(file.readBytes())
        } ?: ""
    }
}
