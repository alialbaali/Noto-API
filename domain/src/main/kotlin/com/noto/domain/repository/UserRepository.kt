package com.noto.domain.repository

import com.noto.domain.model.User

interface UserRepository {

    suspend fun createUser(user: User) : Result<User>

    suspend fun deleteUser(userId: Long) : Result<Long>

    suspend fun updateUser(user: User): Result<User>

    suspend fun loginUser(user: User): Result<User>

    suspend fun checkUserId(userId: Long) : Result<Long>
}