package com.noto.app.service

import com.noto.app.AuthenticationException
import com.noto.app.AuthorizationException
import com.noto.app.util.generateToken
import com.noto.app.util.hash
import com.noto.domain.interactor.user.UserUseCases
import com.noto.domain.model.User
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
class UserService(private val userUseCases: UserUseCases) {


    suspend fun createUser(user: User): String {

        user.userPassword = user.userPassword.hash()

        return userUseCases.createUser(user).getOrElse {
            throw AuthenticationException(it.message)
        }.generateToken()
    }

    suspend fun loginUser(user: User): String {

        user.userPassword = user.userPassword.hash()

        return userUseCases.loginUser(user).getOrElse {
            throw AuthenticationException(it.message)
        }.generateToken()
    }

    suspend fun checkUserId(userId: Long): Long {

        return userUseCases.checkUserId(userId).getOrElse {
            throw AuthorizationException(it.message)
        }

    }

}