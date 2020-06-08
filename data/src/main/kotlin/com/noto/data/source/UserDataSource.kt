package com.noto.data.source

import com.noto.domain.model.User

interface UserDataSource {

    suspend fun createUser(user: User)

    suspend fun updateUser(userId: Long, user: User)

    suspend fun delete(userId: Long)

    suspend fun getUserByEmail(email: String): User?

    suspend fun getUserById(userId: Long) : User?

}