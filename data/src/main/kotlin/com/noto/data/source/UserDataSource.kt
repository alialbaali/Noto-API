package com.noto.data.source

import com.noto.domain.model.User

interface UserDataSource {

    suspend fun createUser(user: User)

    suspend fun delete(userId: Long)

    suspend fun updateUser(user: User)

    suspend fun getUserByUsername(username: String): User?

    suspend fun getUsers(): List<User>

    suspend fun checkUserId(userId: Long): Long?

}