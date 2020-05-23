package com.noto.data.repository

import com.noto.data.source.UserDataSource
import com.noto.domain.model.User
import com.noto.domain.repository.UserRepository

class UserRepositoryImpl(private val source: UserDataSource) : UserRepository {

    override suspend fun createUser(user: User): Result<User> {

        return if (source.getUserByUsername(user.username) == null) {
            source.createUser(user)
            Result.success(source.getUserByUsername(user.username)!!)
        } else {
            Result.failure(Throwable("Username already exists"))
        }
    }

    override suspend fun loginUser(user: User): Result<User> {
        val result = source.getUserByUsername(user.username)
        return when {
            result == null -> Result.failure(Throwable("User doesn't exist"))
            result.userPassword != user.userPassword -> Result.failure(Throwable("Wrong password"))
            else -> Result.success(result)
        }
    }

    override suspend fun updateUser(userId: Long, user: User): Result<User> {
        source.updateUser(userId, user)
        return Result.success(source.getUserById(userId)!!)
    }

    override suspend fun checkUserId(userId: Long): Result<Long> {

        return if (source.getUserById(userId) == null) {
            Result.failure(Throwable("Invalid token"))
        } else {
            Result.success(userId)
        }
    }

    override suspend fun deleteUser(userId: Long): Result<Long> {

        return if (source.getUserById(userId) == null) {
            Result.failure(Throwable("Invalid userId"))
        } else {
            source.delete(userId)
            Result.success(userId)
        }
    }
}