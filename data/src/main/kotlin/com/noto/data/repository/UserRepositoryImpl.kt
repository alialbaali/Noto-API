package com.noto.data.repository

import com.noto.data.source.UserDataSource
import com.noto.domain.model.User
import com.noto.domain.repository.UserRepository

class UserRepositoryImpl(private val source: UserDataSource) : UserRepository {
    override suspend fun createUser(user: User): Result<User> {
        val users = source.getUsers()
        return if (users.any { it.username == user.username }) {
            Result.failure(Throwable("Username already exists"))
        } else {
            source.createUser(user)
            Result.success(source.getUsers().first { it.username == user.username })
        }
    }

    override suspend fun deleteUser(userId: Long): Result<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(user: User): Result<User> {
        val result = source.getUserByUsername(user.username)
        return when {
            result == null -> Result.failure(Throwable("User doesn't exist"))
            result.userPassword != user.userPassword -> Result.failure(Throwable("Wrong password"))
            else -> Result.success(result)
        }
    }
}