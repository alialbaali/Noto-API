package com.alialbaali.noto.domain.repository

import com.alialbaali.noto.domain.model.User

interface UserRepository {

    suspend fun createUser(user: User)

    suspend fun deleteUser(userId: Long)

    suspend fun getUsers(): Result<User>

    suspend fun updateUser(user: User)

    suspend fun getUserById(userId: Long): Result<User>
}