package com.noto.app.service

import com.noto.app.AuthenticationException
import com.noto.app.BadRequestException
import com.noto.app.util.hash
import com.noto.domain.interactor.user.UserUseCases
import com.noto.domain.model.User
import io.ktor.util.KtorExperimentalAPI

/**

This regex will enforce these rules:

At least one upper case English letter, (?=.*?[A-Z])
At least one lower case English letter, (?=.*?[a-z])
At least one digit, (?=.*?[0-9])
At least one special character, (?=.*?[#?!@$%^&*-])
Minimum eight in length .{8,} (with the anchors)

 */
private val PASSWORD_REGEX = """^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@${'$'}%^&*-]).{8,}${'$'}""".toRegex()

private val EMAIL_REGEX =
    """(?:[a-z0-9!#${'$'}%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#${'$'}%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])""".toRegex()

@KtorExperimentalAPI
class UserService(private val userUseCases: UserUseCases) {

    suspend fun createUser(user: User): User {

        if (user.userDisplayName.isEmpty()) throw BadRequestException("Invalid name")

        if (!user.userPassword.matches(EMAIL_REGEX)) throw BadRequestException("Invalid email")

        if (!user.userPassword.matches(PASSWORD_REGEX)) throw BadRequestException("Invalid password")

        user.apply {
            userPassword = userPassword.hash()
        }

        return userUseCases.createUser(user).getOrElse {
            throw AuthenticationException(it.message)
        }
    }

    suspend fun loginUser(user: User): User {

        if (user.userEmail.isEmpty()) throw BadRequestException("Email is required")

        if (user.userPassword.isEmpty()) throw BadRequestException("Password is required")

        user.apply {
            userPassword = userPassword.hash()
        }

        return userUseCases.loginUser(user).getOrElse {
            throw AuthenticationException(it.message)
        }
    }

    suspend fun updateUser(userId: Long, user: User): User {

        user.apply {
            userPassword = userPassword.hash()
        }

        return userUseCases.updateUser(userId, user).getOrElse {
            throw BadRequestException(it.message)
        }
    }

    suspend fun deleteUser(userId: Long): Long {

        return userUseCases.deleteUser(userId).getOrElse {
            throw BadRequestException(it.message)
        }

    }

    suspend fun checkUserId(userId: Long): Long {

        return userUseCases.checkUserId(userId).getOrElse {
            throw BadRequestException(it.message)
        }

    }
}