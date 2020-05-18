package com.noto.app.controller

import com.noto.app.AuthenticationException
import com.noto.domain.interactor.user.UserUseCases
import com.noto.domain.model.User
import com.noto.app.util.generateToken
import com.noto.app.util.hash
import com.noto.app.util.respond
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject


@KtorExperimentalAPI
fun Routing.user(userUseCases: UserUseCases = inject<UserUseCases>().value) {

    route("/user") {

        post("/create") {
            val user = call.receive<User>().also {
                it.userPassword = it.userPassword.hash()
            }

            val token = userUseCases.createUser(user).getOrElse {
                throw AuthenticationException(it.message)
            }.generateToken()

            call.respond(HttpStatusCode.Created, true, data = token)
        }

        post("/login") {
            val user = call.receive<User>().also {
                it.userPassword = it.userPassword.hash()
            }

            val token = userUseCases.loginUser(user).getOrElse {
                throw AuthenticationException(it.message)
            }.generateToken()

            call.respond(HttpStatusCode.OK, true, data = token)
        }
    }
}