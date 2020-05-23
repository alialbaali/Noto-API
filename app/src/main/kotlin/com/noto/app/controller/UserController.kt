package com.noto.app.controller

import com.noto.app.BadRequestException
import com.noto.app.service.UserService
import com.noto.app.util.getResponse
import com.noto.app.util.respond
import com.noto.domain.model.User
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun Routing.user(userService: UserService) {

    route("/user") {

        post("/create") {

            val user = call.receive<User>()

            val data = userService.createUser(user).getResponse()

            call.respond(HttpStatusCode.Created, true, data = data)
        }

        post("/login") {

            val user = call.receive<User>()

            val data = userService.loginUser(user).getResponse()

            call.respond(HttpStatusCode.OK, true, data = data)
        }

        authenticate {

            patch {

                val user = call.receive<User>()

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                val data = userService.updateUser(userId, user).getResponse()

                call.respond(HttpStatusCode.OK, true, data = data)

            }

            delete {

                val userId = call.authentication.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asLong()
                    ?: throw BadRequestException("Missing claims")

                userService.deleteUser(userId)

                call.respond(HttpStatusCode.NoContent, true)
            }

        }
    }
}